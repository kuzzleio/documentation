<template>
  <div class="md-nav__source mobile-only md-nav__mobile-color">
    <div v-if="$route.path.match('sdk-reference')" class="selector-container">
      <SDKSelector :items="sdkList"/>
    </div>

    <p class="md-nav__mobile-group-name">Use</p>
    <router-link
      :to="{path: generateLink('/core/1/guides/')}"
      title="Guides"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">Guides</div>
    </router-link>
    <router-link
      :to="{path: generateLink('/core/1/api/')}"
      title="API"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">API</div>
    </router-link>
    <router-link
      :to="{path: '/sdk/'}"
      title="SDKs"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">SDK</div>
    </router-link>
    <router-link
      :to="{path: generateLink('/core/1/plugins/')}"
      title="Plugins"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">Plugins</div>
    </router-link>
    <p class="md-nav__mobile-group-name">Extend</p>
    <router-link
      :to="{path: generateLink('/core/1/protocols/')}"
      title="Plugins"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository"> Write Plugins</div>
    </router-link>
    <router-link
      :to="{path: generateLink('/core/1/protocols/')}"
      title="Protocols"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository"> Write Protocols</div>
    </router-link>
  </div>
</template>

<script>
import { getValidLinkByRootPath } from '../util.js';
import sdkList from '../sdk.json';

export default {
  data() {
    return {
      sdkList
    };
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
