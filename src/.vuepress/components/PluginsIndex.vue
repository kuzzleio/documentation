<template>
  <div class="Tiles">
    <a
      v-for="plugin in pluginList"
      :key="plugin.name + plugin.version"
      :href="plugin.path"
      class="Tiles-item"
    >
      <img :src="$withBase(plugin.icon)" :alt="plugin.name" class="Tiles-item-logo" />
      <div class="Tiles-item-name">
        {{ `${plugin.name} v${plugin.version}` }}
      </div>
    </a>
  </div>
</template>

<script>
import { usePageData } from 'vuepress/client';

import externalPlugins from '../external-plugins.json';

export default {
  name: 'PluginsIndex',
  methods: {},
  props: {
    kuzzleMajor: {
      type: Number,
      required: true,
    },
  },
  computed: {
    pluginList() {
      return this.page$.sectionList
        .filter(
          (s) =>
            s.kuzzleMajor === this.kuzzleMajor &&
            s.section === 'official-plugins' &&
            s.subsection &&
            // If we are deploying to the master branch, exclude the
            // sections that are not released yet
            (BRANCH === 'master' ? s.released === true : true)
        )
        .concat(externalPlugins[this.kuzzleMajor]);
    },
  },
  setup() {
    return { page$: usePageData() };
  },
};
</script>

<style></style>
