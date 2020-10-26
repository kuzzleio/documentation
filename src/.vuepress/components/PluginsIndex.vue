<template>
  <div class="Tiles">
    <a
      v-for="plugin in pluginList"
      :key="plugin.name + plugin.version"
      :href="plugin.path"
      class="Tiles-item"
    >
      <img :src="plugin.icon" :alt="plugin.name" class="Tiles-item-logo" />
      <div class="Tiles-item-name">
        {{ `${plugin.name} v${plugin.version}` }}
      </div>
    </a>
  </div>
</template>

<script>
import { getItemLocalStorage } from '../util';
import externalPlugins from '../external-plugins.json';

export default {
  name: 'PluginsIndex',
  methods: {},
  computed: {
    kuzzleMajor() {
      if (!this.$page.currentSection) {
        if (!this.$route.query.kuzzleMajor) {
          return 2;
        } else {
          return parseInt(this.$route.query.kuzzleMajor);
        }
      }

      return this.$page.currentSection.kuzzleMajor;
    },
    pluginList() {
      return this.$page.sectionList
        .filter(
          (s) =>
            s.kuzzleMajor === this.kuzzleMajor &&
            s.section === 'official-plugins' &&
            // If we are deploying to the master branch, exclude the
            // sections that are not released yet
            (process.env.BRANCH === 'master' ? s.released === true : true)
        )
        .concat(externalPlugins[this.kuzzleMajor]);
    },
  },
};
</script>

<style></style>
