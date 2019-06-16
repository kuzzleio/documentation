<template>
  <!-- Tabs with outline -->
  <nav class="md-tabs" data-md-component="tabs">
    <div class="md-tabs__inner md-grid">
      <ul class="md-tabs__list">
        <li class="md-tabs__group">
          <p class="md-tabs__group-name">Use</p>
          <ul class="md-tabs__group-items">
            <li class="md-tabs__item">
              <router-link
                :to="{path: generateLink('/core/1/guides/')}"
                :class="{'md-tabs__link--active': $route.path.match('/guides/')}"
                title="Guide"
                class="md-tabs__link"
              >Guides</router-link>
            </li>
            <li class="md-tabs__item">
              <router-link
                :class="{'md-tabs__link--active': $route.path.match('/api/')}"
                :to="{path: generateLink('/core/1/api/')}"
                title="API"
                class="md-tabs__link"
              >API</router-link>
            </li>
            <li class="md-tabs__item">
              <router-link
                :class="{'md-tabs__link--active': $route.path.match('/sdk/')}"
                :to="{path: '/sdk/'}"
                title="SDK"
                class="md-tabs__link"
              >SDK</router-link>
            </li>
            <li class="md-tabs__item">
              <!-- link to Official plugins page -->
              <router-link
                :class="{'md-tabs__link--active': $route.path.match('/plugins/')}"
                :to="{path: generateLink('/core/1/plugins/')}"
                title="Plugins"
                class="md-tabs__link"
              >Plugins</router-link>
            </li>
            <li class="md-tabs__item">
              <router-link
                :class="{'md-tabs__link--active': $route.path.match('/koncorde/')}"
                :to="{path: generateLink('/core/1/koncorde/')}"
                title="Koncorde"
                class="md-tabs__link"
              >Koncorde</router-link>
            </li>
          </ul>
        </li>
        <li class="md-tabs__separator"></li>
        <li class="md-tabs__group">
          <p class="md-tabs__group-name">Extend</p>
          <ul class="md-tabs__group-items">
            <li class="md-tabs__item">
              <a
                :class="{'md-tabs__link--active': $route.path.match('/core/1/plugins/')}"
                :href="generateLink('/core/1/plugins/')"
                title="Plugins"
                class="md-tabs__link"
              >Write Plugins</a>
            </li>
            <li class="md-tabs__item">
              <a
                :class="{'md-tabs__link--active': $route.path.match('/core/1/protocols/')}"
                :href="generateLink('/core/1/protocols/')"
                title="Protocols"
                class="md-tabs__link"
              >Write Protocols</a>
            </li>
          </ul>
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
