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
import { getCurrentVersion } from '../helpers';
export default {
  name: 'PluginsIndex',
  methods: {},
  computed: {
    kuzzleMajor() {
      return getCurrentVersion(this.$page, this.$route);
    },
    pluginList() {
      return this.$page.sectionList
        .filter(
          (s) =>
            s.kuzzleMajor === this.kuzzleMajor &&
            s.section === 'official-plugins'
        )
        .concat(externalPlugins[this.kuzzleMajor]);
    },
  },
};
</script>

<style></style>
