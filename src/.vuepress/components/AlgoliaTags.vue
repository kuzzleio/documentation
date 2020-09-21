<template>
  <div>
    <!-- Algolia Levels -->
    <div data-algolia-lvl="0" style="display: none">{{ algoliaLevel[0] }}</div>
    <div data-algolia-lvl="1" style="display: none">{{ algoliaLevel[1] }}</div>
  </div>
</template>

<script>
export default {
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
};
</script>