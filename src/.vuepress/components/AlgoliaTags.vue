<template>
  <div style="display: none">
    <div data-algolia-lvl="0">{{ algoliaLevel[0] }}</div>
    <div data-algolia-lvl="1">{{ algoliaLevel[1] }}</div>
    <div data-algolia="major-version">{{ kuzzleMajor }}</div>
  </div>
</template>

<script>
export default {
  props: {
    kuzzleMajor: {
      type: Number,
      required: true,
    },
  },
  computed: {
    algoliaLevel() {
      if (!this.$page.currentSection) {
        return ['Kuzzle', 'Documentation'];
      }

      if (this.$page.currentSection.section === 'sdk') {
        return [
          'SDK',
          `SDK ${this.$page.currentSection.name} v${this.$page.currentSection.version}.x`,
        ];
      }

      if (this.$page.currentSection.section === 'how-to') {
        return [
          `How-To (Kuzzle v${this.$page.currentSection.kuzzleMajor}.x)`,
          `${this.$page.currentSection.name}`,
        ];
      }

      if (this.$page.currentSection.section === 'official-plugins') {
        return [
          `Official Plugins (Kuzzle v${this.$page.currentSection.kuzzleMajor}.x)`,
          `${this.$page.currentSection.name} v${this.$page.currentSection.version}.x`,
        ];
      }

      return [
        'Core',
        `${this.$page.currentSection.name} v${
          this.$page.currentSection.version
            ? this.$page.currentSection.version
            : this.$page.currentSection.kuzzleMajor
        }.x`,
      ];
    },
  },
  mounted() {
    // https://docsearch.algolia.com/docs/required-configuration/#introduce-global-information-as-meta-tags
    const head = document.head;
    const tag = document.createElement('meta');
    tag.setAttribute('property', 'docsearch:version');
    tag.setAttribute('content', `${this.kuzzleMajor}.0.0`);
    head.appendChild(tag);
  },
};
</script>