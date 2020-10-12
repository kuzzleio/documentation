<template>
  <div class="md-nav__source mobile-only md-nav__mobile-color">
    <div v-if="$route.path.match('sdk-reference')" class="selector-container">
      <SDKSelector :items="sdkList" :kuzzleMajor="kuzzleMajor" />
    </div>
    <div v-for="([part, links]) of headerEntries">
      <a
        v-for="link of links"
        :href="link.path"
        :title="link.label"
        class="md-source"
        data-md-state="done"
        @click="$emit('closeSidebar')"
      >
        <div class="md-source__repository">{{ link.label }}</div>
      </a>
    </div>
  </div>
</template>

<script>
import { getValidLinkByRootPath } from '../util.js';
import sdks from '../sdk.json';
import headerEntriesJson from '../header-entries.json';

const { getItemLocalStorage } = require('../util.js');

export default {
  props: ['kuzzleMajor'],
  computed: {
    sdkList() {
      return sdks[this.kuzzleMajor] || [];
    },
    headerEntries() {
      return Object.entries(headerEntriesJson[this.kuzzleMajor]);
    }
  },
  methods: {
    generateLink(path) {
      return getValidLinkByRootPath(path, this.$site.pages);
    }
  }
};
</script>

<style>
</style>
