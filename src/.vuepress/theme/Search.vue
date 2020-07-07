<template>
  <div class="md-search" data-md-component="search" role="dialog">
    <label class="md-search__overlay" for="search"></label>
    <div class="md-search__inner" role="search">
      <form
        name="search"
        id="search-form"
        class="algolia-search-wrapper search-box md-search__form"
        role="search"
      >
        <input
          id="algolia-search-input"
          class="search-query md-search__input"
          placeholder="Search (s)"
          autocapitalize="off"
          autocorrect="off"
          autocomplete="off"
          spellcheck="false"
          data-md-component="query"
          data-md-state="active"
          ref="searchInput"
          :placeholder="placeholder"
        />
        <label class="md-icon md-search__icon" for="search"></label>
        <button
          type="reset"
          class="md-icon md-search__icon search-reset-button"
          data-md-component="reset"
          tabindex="-1"
          @click="query = ''"
        >
          
          <!-- close -->
        </button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AlgoliaSearchBox',

  data() {
    return {
      placeholder: 'Search',
      algoliaOptions: {
        apiKey: 'c6452010dc26eb671d637214f1110c91',
        indexName: 'kuzzle',
        inputSelector: '#algolia-search-input',
        debug: false
      }
    };
  },

  mounted() {
    this.initialize(this.options, this.$lang);
  },

  methods: {
    initialize(userOptions, lang) {
      Promise.all([
        import(
          /* webpackChunkName: "docsearch" */ 'docsearch.js/dist/cdn/docsearch.min.js'
        ),
        import(
          /* webpackChunkName: "docsearch" */ 'docsearch.js/dist/cdn/docsearch.min.css'
        )
      ]).then(([docsearch]) => {
        docsearch = docsearch.default;
        docsearch({
          ...this.algoliaOptions,
          handleSelected: (input, event, suggestion) => {
            const { pathname, hash } = new URL(suggestion.url);
            const routepath = pathname.replace(this.$site.base, '/');
            const _hash = decodeURIComponent(hash);
            this.$router.push(`${routepath}${_hash}`);
          },
          transformData: function(hits) {
            console.log(hits);
          }
        });
      });
    }
  }
};
</script>
