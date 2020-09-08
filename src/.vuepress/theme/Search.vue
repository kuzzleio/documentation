<template>
  <div class="md-search" data-md-component="search" role="dialog">
    <label class="md-search__overlay" for="search"></label>
    <div class="md-search__inner" role="search">
      <form class="md-search__form" name="search" @submit.prevent="goToHighlightedResult">
        <input
          id="algolia-search-input"
          class="search-query md-search__input"
          placeholder="Search (s)"
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
  props: ['options'],
  data() {
    return {
      placeholder: undefined,
    };
  },
  watch: {
    $lang(newValue) {
      this.update(this.options, newValue);
    },
    options(newValue) {
      this.update(newValue, this.$lang);
    },
  },
  mounted() {
    this.initialize(this.options, this.$lang);
    this.placeholder = this.$site.themeConfig.searchPlaceholder || '';
  },
  methods: {
    initialize(userOptions, lang) {
      Promise.all([
        import(
          /* webpackChunkName: "docsearch" */ 'docsearch.js/dist/cdn/docsearch.min.js'
        ),
        import(
          /* webpackChunkName: "docsearch" */ 'docsearch.js/dist/cdn/docsearch.min.css'
        ),
      ]).then(([docsearch]) => {
        docsearch = docsearch.default;
        docsearch(
          {
            inputSelector: '#algolia-search-input',
            apiKey: 'c6452010dc26eb671d637214f1110c91',
            indexName: 'kuzzle',
          }
          // Object.assign({}, userOptions, {

          //   #697 Make docsearch work well at i18n mode.
          //   algoliaOptions: Object.assign(
          //     {
          //       facetFilters: [`lang:${lang}`].concat(
          //         algoliaOptions.facetFilters || []
          //       ),
          //     },
          //     algoliaOptions
          //   ),
          //   handleSelected: (input, event, suggestion) => {
          //     const { pathname, hash } = new URL(suggestion.url);
          //     const routepath = pathname.replace(this.$site.base, '/');
          //     const _hash = decodeURIComponent(hash);
          //     this.$router.push(`${routepath}${_hash}`);
          //   },
          // })
        );
      });
    },
    update(options, lang) {
      this.$el.innerHTML =
        '<input id="algolia-search-input" class="search-query">';
      this.initialize(options, lang);
    },
  },
};
</script>

<style lang="scss">
/* Main dropdown wrapper */
.ds-dropdown-menu {
  font-size: 15px;
}
</style>