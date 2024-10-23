<template>
  <div>
    <HowToCategory
      v-for="cat in categories"
      :key="cat"
      :categorie="cat"
      :howToList="howToListByCategories[cat]"
    />
  </div>
</template>

<script>
import { transform } from 'lodash-es';
import { usePageData } from 'vuepress/client';

export default {
  name: 'HowToIndex',
  props: {
    kuzzleMajor: {
      type: Number,
      required: true,
    },
  },
  computed: {
    howToList() {
      return this.page$.sectionList.filter(
        (s) =>
          s.kuzzleMajor === this.kuzzleMajor &&
          s.section === 'how-to' &&
          s.subsection &&
          // If we are deploying to the master branch, exclude the
          // sections that are not released yet
          (BRANCH === 'master' ? s.released === true : true)
      );
    },
    howToListByCategories() {
      return transform(
        this.howToList,
        (result, value, key) => {
          if (!result[value.subsection]) {
            result[value.subsection] = [];
          }
          result[value.subsection].push(value);
        },
        {}
      );
    },
    categories() {
      return Object.keys(this.howToListByCategories);
    },
  },
  setup() {
    return { page$: usePageData() };
  },
};
</script>

<style>
</style>
