<template>
  <div
    class="md-sidebar md-sidebar--primary"
    :class="{ 'md-sidebar--open': sidebarOpen }"
    data-md-component="navigation"
  >
    <div class="md-sidebar__scrollwrap" ref="scrollwrap">
      <div class="md-sidebar__inner">
        <nav class="md-nav md-nav--primary" data-md-level="0">
          <label
            class="md-nav__title md-nav__title--site mobile-only"
            for="drawer"
          >
            <span class="md-nav__button md-logo">
              <img
                :src="$withBase('/logo-min.png')"
                alt="kuzzle logo mini"
                width="48"
                height="48"
              />
            </span>
            <NavSelector :items="products" label="Change product" />
          </label>
          <div class="mobile-only">
            <TopMenu v-if="isBackend" />
            <TopMenuV1 :kuzzle-major="kuzzleMajor" v-if="kuzzleMajor === 1" />
          </div>
          <SDKSelector
            class="md-sidebar--selector"
            v-if="sdkOrApiPage"
            :items="sdkList"
            :kuzzleMajor="kuzzleMajor"
          />
          <!-- Render item list -->
          <ul class="md-nav__list" data-md-scrollfix>
            <li
              v-for="item__1 in getPageChildren(root).filter(
                (p) => p.meta.frontmatter.type === 'branch'
              )"
              :key="item__1.path"
              class="md-nav__item-container"
            >
              <ul class="md-nav__group">
                <li
                  class="md-nav__separator"
                  :data-algolia-lvl="
                    page$.path.startsWith(item__1.path) ? '2' : ''
                  "
                >
                  {{ shortTitle(item__1.meta.frontmatter.title) }}
                </li>

                <li
                  v-for="item__2 in getPageChildren(item__1)"
                  :key="item__2.path"
                  class="md-nav__item md-nav-title"
                >
                  <div
                    class="md-nav__link"
                    :class="{
                      'md-nav__link--active': page$.path === item__2.path,
                      'md-nav__item--code':
                        item__2.meta.frontmatter.code == true,
                    }"
                  >
                    <button
                      v-if="getPageChildren(item__2).length"
                      type="button"
                      class="md-nav__toggle"
                      :aria-expanded="isSubmenuOpen(item__1, item__2) ? 'true' : 'false'"
                      @click="handleSubmenuClick(item__1, item__2)"
                    >
                      <font-awesome-icon
                        class="md-nav__caret"
                        :icon="
                          isSubmenuOpen(item__1, item__2)
                            ? 'fa-solid fa-caret-down'
                            : 'fa-solid fa-caret-right'
                        "
                      />
                      <span
                        :data-algolia-lvl="
                          page$.path.startsWith(item__2.path) ? '3' : ''
                        "
                        >{{ shortTitle(item__2.meta.frontmatter.title) }}</span
                      >
                    </button>
                    <RouteLink
                      v-else
                      :to="item__2.path"
                      @click.native="closeSidebar"
                    >
                      {{ shortTitle(item__2.meta.frontmatter.title) }}
                    </RouteLink>
                  </div>
                  <ul
                    class="md-nav__list sub-menu"
                    :class="subMenuClass(item__1, item__2)"
                    :id="
                      getId([
                        item__1.meta.frontmatter.title,
                        item__2.meta.frontmatter.title,
                      ])
                    "
                  >
                    <li
                      v-for="item__3 of getPageChildren(item__2)"
                      :key="item__3.path"
                      :id="
                        getId([
                          item__1.meta.frontmatter.title,
                          item__2.meta.frontmatter.title,
                          item__3.meta.frontmatter.title,
                        ])
                      "
                      class="md-nav__item"
                    >
                      <RouteLink
                        :to="item__3.path"
                        :title="shortTitle(item__3.meta.frontmatter.title)"
                        @click.native="$emit('closeSidebar')"
                        :class="{
                          'md-nav__item--code': item__3.meta.frontmatter.code,
                          'md-nav__link': page$.path !== item__3.path,
                          'md-nav__link--active': page$.path === item__3.path,
                        }"
                      >
                        {{ shortTitle(item__3.meta.frontmatter.title) }}
                      </RouteLink>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
import {
  resolveRoute,
  usePageData,
  useRoute,
  useRouter,
  useRoutes,
  useSiteData,
} from 'vuepress/client';

import TopMenu from './TopMenu.vue';
import TopMenuV1 from './TopMenuV1.vue';
import NavSelector from '../components/NavSelector.vue';
import { BACKEND_ID, PRODUCTS, absolutePath, findEntry } from '../products';

import { shortTitle } from '../helpers';
import {
  getPageChildren,
  findRootNode,
  setItemLocalStorage,
  getNodeByPath,
} from '../util';

export default {
  components: {
    TopMenu,
    TopMenuV1,
    NavSelector,
  },
  props: {
    sidebarOpen: {
      type: Boolean,
      default: false,
    },
    kuzzleMajor: {
      type: Number,
      default: null,
    },
    sdkList: {
      type: Array,
      required: true,
    },
  },
  setup() {
    return {
      page$: usePageData(),
      route$: useRoute(),
      router$: useRouter(),
      routes$: useRoutes(),
      site$: useSiteData(),
    };
  },
  data() {
    return {
      openedSubmenu: '',
    };
  },
  computed: {
    products() {
      return PRODUCTS;
    },
    isBackend() {
      const product = findEntry(
        PRODUCTS,
        absolutePath(this.site$.base, this.route$.path)
      );

      return Boolean(product) && product.id === BACKEND_ID;
    },
    sdkOrApiPage() {
      if (!this.page$.currentSection) {
        return false;
      }

      return (
        this.page$.currentSection.section === 'sdk' ||
        this.page$.currentSection.subsection === 'api'
      );
    },
    root() {
      const nodes = this.pages.map((page) => ({
        frontmatter: page.meta.frontmatter,
        path: page.path,
      }));

      return findRootNode(this.page$, nodes);
    },
    pages() {
      return Object.keys(this.routes$).map(resolveRoute);
    },
  },
  methods: {
    shortTitle,
    isSubmenuOpen(item__1, item__2) {
      return (
        this.openedSubmenu ===
        this.getId([
          item__1.meta.frontmatter.title,
          item__2.meta.frontmatter.title,
        ])
      );
    },
    setOpenedSubmenu(item__1, item__2) {
      setItemLocalStorage('item__1', item__1);
      setItemLocalStorage('item__2', item__2);
      this.openedSubmenu = this.getId([
        item__1.meta.frontmatter.title,
        item__2.meta.frontmatter.title,
      ]);
    },
    unsetOpenedSubmenu() {
      localStorage.setItem('item__1', null);
      localStorage.setItem('item__2', null);
      this.openedSubmenu = '';
    },
    closeSubmenu() {
      if (this.openedSubmenu === '') {
        return;
      }
      const openedSubmenuId = this.sanitize(this.openedSubmenu);
      if (!document.getElementById(openedSubmenuId)) {
        return;
      }
      document.getElementById(openedSubmenuId).style.height = '0px';
    },
    redirect(item__2) {
      this.closeSidebar();
      this.unsetOpenedSubmenu();
      this.router$.push(item__2.path);
    },
    openSubmenu(item__1, item__2) {
      const childs = this.getPageChildren(item__2);
      if (!childs) {
        return;
      }

      const item2Id = this.getId([
        item__1.meta.frontmatter.title,
        item__2.meta.frontmatter.title,
      ]);
      const item = document.getElementById(item2Id);

      if (item) {
        item.style.height = 'auto';
      }
    },
    closeSidebar(item) {
      this.$emit('closeSidebar');
    },
    subMenuClass(item__1, item__2) {
      return this.openedSubmenu ===
        this.getId([
          item__1.meta.frontmatter.title,
          item__2.meta.frontmatter.title,
        ])
        ? 'displaySubmenu'
        : '';
    },
    getId(itemsTitle) {
      return itemsTitle.reduce(
        (id, item) => id + '_' + this.sanitize(item),
        ''
      );
    },
    sanitize(str) {
      return str.replace(/ /g, '_');
    },
    handleSubmenuClick(item__1, item__2) {
      const childs = this.getPageChildren(item__2);
      const clickedSubmenuId = this.getId([
        item__1.meta.frontmatter.title,
        item__2.meta.frontmatter.title,
      ]);

      if (!childs.length) {
        this.redirect(item__2);
      } else if (this.openedSubmenu === clickedSubmenuId) {
        this.closeSubmenu();
        this.unsetOpenedSubmenu();
      } else if (document.getElementById(clickedSubmenuId)) {
        this.closeSubmenu();
        this.openSubmenu(item__1, item__2);
        this.setOpenedSubmenu(item__1, item__2);
      }
    },

    getPageChildren(page) {
      return getPageChildren(page, this.pages);
    },
    /**
     * @param {Element} target
     */
    isInViewport(target) {
      const rect = target.getBoundingClientRect();

      return (
        rect.bottom > 0 &&
        rect.right > 0 &&
        rect.left <
          (window.innerWidth || document.documentElement.clientWidth) &&
        rect.top < (window.innerHeight || document.documentElement.clientHeight)
      );
    },
    openCurrentSubmenu() {
      let path = this.route$.path;
      const splitted = path.split('/');
      const item__2Path = path.replace(`${splitted[splitted.length - 2]}/`, '');
      const item__1Path = item__2Path.replace(
        `${splitted[splitted.length - 3]}/`,
        ''
      );
      const item__1 = getNodeByPath(item__1Path, this.pages);
      const item__2 = getNodeByPath(item__2Path, this.pages);
      if (!item__1 || !item__2) {
        return;
      }
      this.closeSubmenu();
      this.openSubmenu(item__1, item__2);
      this.setOpenedSubmenu(item__1, item__2);
    },
    scrollToActiveItem() {
      document.onreadystatechange = () => {
        if (document.readyState === 'complete') {
          const activeLink = this.$el.querySelector('.md-nav__link--active');
          if (activeLink && !this.isInViewport(activeLink)) {
            const activeDiv = activeLink.parentElement.parentElement;
            const scroll =
              activeDiv.offsetTop + activeDiv.offsetParent.offsetTop - 50;
            this.$refs.scrollwrap.scrollTop = scroll;
          }
        }
      };
    },
  },
  mounted() {
    this.openCurrentSubmenu();
    this.scrollToActiveItem();
  },
};
</script>
