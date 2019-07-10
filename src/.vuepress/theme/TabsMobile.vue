<template>
  <div class="md-nav__source mobile-only md-nav__mobile-color">
    <div v-if="$route.path.match('sdk-reference')" class="selector-container">
      <SDKSelector :items="sdkList" />
    </div>
    <div v-for="part of Object.keys(links)">
      <p class="md-nav__mobile-group-name">{{ part }}</p>
      <router-link
        v-for="link of links[part]"
        :to="getPath(link)"
        :title="link.label"
        class="md-source"
        data-md-state="done"
        style="display:inline-block;"
        @click.native="$emit('closeSidebar')"
      >
        <div class="md-source__repository">{{ link.label }} </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import { getValidLinkByRootPath } from '../util.js';
import sdkList from '../sdk.json';

export default {
  props: ['links'],
  data() {
    return {
      sdkList
    };
  },
  methods: {
    getPath(link) {
      return {path: link.generateLink? this.generateLink(link.path): link.path};
    },
    generateLink(path) {
      return getValidLinkByRootPath(path, this.$site.pages);
    }
  }
};
</script>

<style>
</style>
