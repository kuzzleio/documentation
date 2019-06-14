<template>
  <!-- Tabs with outline -->
  <nav class="md-tabs" data-md-component="tabs">
    <div class="md-tabs__inner md-grid">
      <ul class="md-tabs__list">
        <li class="md-tabs__item">
          <router-link
            :to="{path: generateLink('/core/1/guides/')}"
            :class="{'md-tabs__link--active': startWith($route.path, '/core/1/guides/')}"
            title="Guide"
            class="md-tabs__link"
          >Guides</router-link>
        </li>
        <li class="md-tabs__item">
          <router-link
            :class="{'md-tabs__link--active': startWith($route.path, '/core/1/api/')}"
            :to="{path: generateLink('/core/1/api/')}"
            title="API"
            class="md-tabs__link"
          >API</router-link>
        </li>
        <li class="md-tabs__item">
          <router-link
            :class="{'md-tabs__link--active': startWith($route.path, '/sdk/')}"
            :to="{path: '/sdk/'}"
            title="SDK"
            class="md-tabs__link"
          >SDK</router-link>
        </li>
        <li class="md-tabs__item">
          <router-link
            :class="{'md-tabs__link--active': startWith($route.path, '/core/1/plugins/')}"
            :to="{path: generateLink('/core/1/plugins/')}"
            title="Plugins"
            class="md-tabs__link"
          >Plugins</router-link>
        </li>
        <li class="md-tabs__item">
          <router-link
            :class="{'md-tabs__link--active': startWith($route.path, '/core/1/protocols/')}"
            :to="{path: generateLink('/core/1/protocols/')}"
            title="Protocols"
            class="md-tabs__link"
          >Protocols</router-link>
        </li>
        <li class="md-tabs__item">
          <router-link
            :class="{'md-tabs__link--active': startWith($route.path, '/core/1/koncorde/')}"
            :to="{path: generateLink('/core/1/koncorde/')}"
            title="Koncorde"
            class="md-tabs__link"
          >Koncorde</router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { getValidLinkByRootPath } from '../util.js';

export default {
  methods: {
    startWith (str, start) {
      return str.indexOf(start) === 0;
    },
    generateLink(path) {
      return getValidLinkByRootPath(path, this.$site.pages);
    }
  }
};
</script>

<style>
</style>
