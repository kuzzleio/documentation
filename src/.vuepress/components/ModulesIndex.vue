<template>
  <div class="Tiles">
    <a
      v-for="mod in modList"
      :key="mod.name + mod.version"
      :href="mod.path"
      class="Tiles-item"
    >
      <img :src="$withBase(mod.icon)" :alt="mod.name" class="Tiles-item-logo" />
      <div class="Tiles-item-name">
        {{ `${mod.name} v${mod.version}` }}
      </div>
    </a>
  </div>
</template>

<script>
import { usePageData } from 'vuepress/client';

export default {
  name: 'ModulesIndex',
  methods: {},
  props: {
    kuzzleMajor: {
      type: Number,
      required: true,
    },
  },
  computed: {
    modList() {
      return this.page$.sectionList
        .filter(
          (s) =>
            s.kuzzleMajor === this.kuzzleMajor &&
            s.section === 'modules' &&
            s.subsection &&
            // If we are deploying to the master branch, exclude the
            // sections that are not released yet
            (BRANCH === 'master' ? s.released === true : true)
        )
    },
  },
  setup() {
    return { page$: usePageData() };
  },
};
</script>

<style></style>
