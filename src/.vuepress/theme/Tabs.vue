<template>
  <!-- Tabs with outline -->
  <nav class="md-tabs" data-md-component="tabs">
    <div class="md-tabs__inner md-grid">
      <ul class="md-tabs__list">
        <li class="md-tabs__item">
          <a
            :class="{'md-tabs__link--active': $route.path.match('/guides/')}"
            :href="generateLink('/core/1/guides/')"
            title="Guide"
            class="md-tabs__link"
          >Guides</a>
        </li>
        <li class="md-tabs__item">
          <a
            :class="{'md-tabs__link--active': $route.path.match('/api/')}"
            :href="generateLink('/core/1/api/')"
            title="API"
            class="md-tabs__link"
          >API</a>
        </li>
        <li class="md-tabs__item">
          <a
            :class="{'md-tabs__link--active': $route.path.match('/sdk/')}"
            href="/sdk/"
            title="SDK"
            class="md-tabs__link"
          >SDK</a>
        </li>
        <li class="md-tabs__item">
          <a
            :class="{'md-tabs__link--active': $route.path.match('/plugins/')}"
            :href="generateLink('/core/1/plugins/')"
            title="Plugins"
            class="md-tabs__link"
          >Plugins</a>
        </li>
        <li class="md-tabs__item">
          <a
            :class="{'md-tabs__link--active': $route.path.match('/protocols/')}"
            :href="generateLink('/core/1/protocols/')"
            title="Protocols"
            class="md-tabs__link"
          >Protocols</a>
        </li>
        <li class="md-tabs__item">
          <a
            :class="{'md-tabs__link--active': $route.path.match('/koncorde/')}"
            :href="generateLink('/core/1/koncorde/')"
            title="Koncorde"
            class="md-tabs__link"
          >Koncorde</a>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { getValidLinkByRootPath } from '../util.js';

export default {
  methods: {
    generateLink(path) {
      return getValidLinkByRootPath(path, this.$site.pages);
    }
  }
};
</script>

<style>
</style>
