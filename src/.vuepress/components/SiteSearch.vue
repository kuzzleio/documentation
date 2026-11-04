<template>
  <div class="site-search">
    <button
      type="button"
      class="site-search__button"
      aria-label="Search the documentation"
      @click="open"
    >
      <font-awesome-icon icon="fa-solid fa-magnifying-glass" />
      <span class="site-search__button-label">Search</span>
      <kbd class="site-search__button-key">{{ shortcutLabel }}</kbd>
    </button>

    <!-- VuePress does not render teleports server-side: mount it client-side
         only, or hydration fails and the whole page goes blank -->
    <ClientOnly>
      <Teleport to="body">
        <div
          v-if="isOpen"
          class="site-search__overlay"
          @mousedown.self="close"
        >
          <div
            class="site-search__modal"
            role="dialog"
            aria-modal="true"
            aria-label="Search the documentation"
          >
            <div class="site-search__field">
              <font-awesome-icon icon="fa-solid fa-magnifying-glass" />
              <input
                ref="input"
                v-model="query"
                type="search"
                placeholder="Search the documentation"
                autocomplete="off"
                spellcheck="false"
                aria-controls="site-search-results"
                :aria-activedescendant="
                  results.length ? `site-search-result-${active}` : undefined
                "
                @keydown.down.prevent="move(1)"
                @keydown.up.prevent="move(-1)"
                @keydown.enter.prevent="go(results[active])"
                @keydown.esc.prevent="close"
              />
              <button type="button" class="site-search__close" @click="close">
                Esc
              </button>
            </div>

            <p v-if="status" class="site-search__status">{{ status }}</p>

            <ul
              v-if="results.length"
              id="site-search-results"
              class="site-search__results"
              role="listbox"
            >
              <li
                v-for="(result, i) in results"
                :id="`site-search-result-${i}`"
                :key="result.url"
                role="option"
                :aria-selected="i === active"
                :class="{ 'site-search__result--active': i === active }"
                class="site-search__result"
                @mousemove="active = i"
                @click="go(result)"
              >
                <span class="site-search__result-section">
                  {{ result.section }}
                  <font-awesome-icon
                    v-if="result.private"
                    icon="fa-solid fa-lock"
                    title="Customer access"
                  />
                </span>
                <span class="site-search__result-title">{{ result.title }}</span>
                <!-- Pagefind escapes the indexed text, only <mark> is added -->
                <span class="site-search__result-excerpt" v-html="result.excerpt" />
              </li>
            </ul>
          </div>
        </div>
      </Teleport>
    </ClientOnly>
  </div>
</template>

<script>
import { hasSessionCookie } from '../auth';

/**
 * Site-wide search, backed by Pagefind (ADR-0006).
 *
 * Each VuePress instance (each section) ships its own index under its path,
 * see pagefind-plugin.ts. The root index is the primary one, the others are
 * merged into it the first time the search opens. SEARCH_INDEXES lists them,
 * built from .repos/repositories.json in search-indexes.ts.
 *
 * Private sections: their index sits behind the same CloudFront rule as their
 * pages. It is only requested when the visitor looks signed in, and checked
 * before being merged, so an expired session only drops those results.
 */
const MAX_RESULTS = 12;
const DEBOUNCE_MS = 150;

const normalize = (p) => (p.endsWith('/') ? p : `${p}/`);

export default {
  name: 'SiteSearch',
  props: {
    kuzzleMajor: {
      type: Number,
      default: 2,
    },
  },
  data() {
    return {
      isOpen: false,
      query: '',
      results: [],
      active: 0,
      status: '',
      shortcutLabel: 'Ctrl K',
    };
  },
  watch: {
    query() {
      clearTimeout(this.debounce);
      this.debounce = setTimeout(this.search, DEBOUNCE_MS);
    },
    kuzzleMajor() {
      // Another major means another set of indexes.
      this.pagefind = null;
      this.results = [];
    },
  },
  mounted() {
    if (/Mac|iPhone|iPad/.test(navigator.platform)) {
      this.shortcutLabel = '⌘ K';
    }

    document.addEventListener('keydown', this.onShortcut);
  },
  beforeUnmount() {
    document.removeEventListener('keydown', this.onShortcut);
    clearTimeout(this.debounce);
  },
  methods: {
    onShortcut(event) {
      const isK = event.key === 'k' || event.key === 'K';

      if (isK && (event.metaKey || event.ctrlKey)) {
        event.preventDefault();
        this.isOpen ? this.close() : this.open();
      }
    },

    async open() {
      this.isOpen = true;
      document.body.style.overflow = 'hidden';
      await this.$nextTick();
      this.$refs.input?.focus();
      this.load();
    },

    close() {
      this.isOpen = false;
      document.body.style.overflow = '';
    },

    /** Indexes of the current major, private ones only for signed-in visitors. */
    indexes() {
      const signedIn = hasSessionCookie(document.cookie);

      return SEARCH_INDEXES.filter(
        (index) =>
          (index.kuzzleMajor === null ||
            index.kuzzleMajor === this.kuzzleMajor) &&
          (!index.private || signedIn)
      );
    },

    load() {
      if (!this.pagefind) {
        this.pagefind = this.createInstance().catch((error) => {
          this.pagefind = null;
          throw error;
        });
      }

      return this.pagefind;
    },

    async createInstance() {
      const pagefind = await import(
        /* @vite-ignore */ `${window.location.origin}/pagefind/pagefind.js`
      );

      await pagefind.options({ excerptLength: 24 });

      const others = this.indexes().filter((index) => index.path !== '/');

      // Pagefind gives up on the whole search when one merged index fails to
      // load. A private index answers 403 (or the sign-in page) without a
      // valid session, and a section may not be deployed yet: only merge the
      // indexes whose entry file really is there.
      const reachable = await Promise.all(
        others.map(async (index) => {
          try {
            const response = await fetch(
              `${index.path}pagefind/pagefind-entry.json`,
              { credentials: 'same-origin' }
            );
            const type = response.headers.get('content-type') || '';

            return response.ok && type.includes('json') ? index : null;
          } catch {
            return null;
          }
        })
      );

      await Promise.all(
        reachable
          .filter(Boolean)
          .map((index) =>
            pagefind
              .mergeIndex(`${index.path}pagefind/`, { baseUrl: index.path })
              .catch(() => {})
          )
      );

      await pagefind.init();

      return pagefind;
    },

    sectionOf(url) {
      const match = SEARCH_INDEXES.filter((index) =>
        url.startsWith(normalize(index.path))
      ).sort((a, b) => b.path.length - a.path.length)[0];

      return match || { label: 'Kuzzle', private: false };
    },

    async search() {
      const query = this.query.trim();

      if (!query) {
        this.results = [];
        this.status = '';
        return;
      }

      let pagefind;

      try {
        pagefind = await this.load();
      } catch {
        this.status =
          'Search is not available here: the index is only built with the site.';
        return;
      }

      const response = await pagefind.search(query);

      // A newer query was typed while this one ran.
      if (query !== this.query.trim()) {
        return;
      }

      const data = await Promise.all(
        response.results.slice(0, MAX_RESULTS).map((result) => result.data())
      );

      this.results = data.map((page) => {
        const section = this.sectionOf(page.url);

        return {
          url: page.url,
          // Pages without an h1 fall back to the <title>, site name included
          title: (page.meta?.title || page.url).replace(
            / \| Kuzzle Documentation$/,
            ''
          ),
          excerpt: page.excerpt,
          section: section.label,
          private: section.private,
        };
      });
      this.active = 0;
      this.status = this.results.length ? '' : `No results for “${query}”.`;
    },

    move(step) {
      if (!this.results.length) {
        return;
      }

      const count = this.results.length;
      this.active = (this.active + step + count) % count;

      this.$nextTick(() => {
        document
          .getElementById(`site-search-result-${this.active}`)
          ?.scrollIntoView({ block: 'nearest' });
      });
    },

    go(result) {
      if (!result) {
        return;
      }

      this.close();

      // Results usually belong to another VuePress instance: full page load.
      window.location.assign(result.url);
    },
  },
};
</script>

<style lang="scss">
.site-search__button {
  display: flex;
  align-items: center;
  gap: 0.8rem;
  height: 3.6rem;
  padding: 0 1.2rem;
  border: 1px solid hsla(0, 0%, 100%, 0.3);
  border-radius: 1.8rem;
  background: hsla(0, 0%, 100%, 0.08);
  color: var(--c-text-on-header);
  font-size: 1.4rem;
  cursor: pointer;

  &:hover,
  &:focus-visible {
    background: hsla(0, 0%, 100%, 0.16);
  }
}

.site-search__button-key {
  padding: 0.1rem 0.6rem;
  border-radius: 0.4rem;
  background: hsla(0, 0%, 100%, 0.16);
  font-family: inherit;
  font-size: 1.1rem;
}

@media (max-width: 960px) {
  .site-search__button-label,
  .site-search__button-key {
    display: none;
  }
}

.site-search__overlay {
  position: fixed;
  inset: 0;
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding: 8vh 1.6rem 1.6rem;
  background: var(--c-bg-overlay);
}

.site-search__modal {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 64rem;
  max-height: 80vh;
  border-radius: 0.8rem;
  background: var(--c-bg-elevated);
  color: var(--c-text);
  box-shadow: 0 1.2rem 4rem rgba(0, 0, 0, 0.3);
  overflow: hidden;
}

.site-search__field {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 1.2rem 1.6rem;
  border-bottom: 1px solid var(--c-border);
  color: var(--c-accent);

  input {
    flex: 1;
    min-width: 0;
    border: 0;
    outline: 0;
    background: transparent;
    color: var(--c-text);
    font-size: 1.7rem;
  }
}

.site-search__close {
  padding: 0.2rem 0.8rem;
  border: 1px solid var(--c-border-strong);
  border-radius: 0.4rem;
  background: transparent;
  color: var(--c-text-muted);
  font-size: 1.1rem;
  cursor: pointer;
}

.site-search__status {
  margin: 0;
  padding: 1.6rem;
  color: var(--c-text-muted);
  font-size: 1.4rem;
}

.site-search__results {
  margin: 0;
  padding: 0.8rem;
  list-style: none;
  overflow-y: auto;
}

.site-search__result {
  display: flex;
  flex-direction: column;
  gap: 0.2rem;
  padding: 1rem 1.2rem;
  border-radius: 0.6rem;
  cursor: pointer;

  &--active {
    background: var(--c-bg-soft);
  }
}

.site-search__result-section {
  color: var(--c-text-muted);
  font-size: 1.2rem;

  svg {
    margin-left: 0.4rem;
  }
}

.site-search__result-title {
  color: var(--c-accent);
  font-size: 1.5rem;
  font-weight: 600;
}

.site-search__result-excerpt {
  color: var(--c-text-muted);
  font-size: 1.3rem;
  line-height: 1.5;

  mark {
    background: var(--c-accent-soft);
    color: var(--c-text);
  }
}
</style>
