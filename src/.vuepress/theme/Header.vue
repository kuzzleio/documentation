<template>
  <div>
    <header class="md-header" data-md-component="header">
      <!-- Top-level navigation -->
      <nav class="md-header-nav">
        <!-- Link to home -->
        <div>
          <a href="/" class="md-header-nav__button md-logo">
            <img
              :src="$withBase('/logo-min.png')"
              alt="kuzzle logo mini"
              width="40"
              height="40"
            />
          </a>
        </div>

        <!-- Button to toggle drawer -->
        <div>
          <label
            class="md-header-nav__button drawer-icon"
            for="drawer"
            @click="$emit('openSidebar')"
          >
            <font-awesome-icon icon="fa-solid fa-bars" size="sm" />
          </label>
        </div>

        <!-- Header title -->
        <div data-md-component="title">
          <span>
            <MajorVersionSelector :kuzzle-major="kuzzleMajor" />
          </span>
        </div>

        <div class="md-header-nav__top-menu-container">
          <div class="screen-only">
            <TopMenu :kuzzle-major="kuzzleMajor" v-if="kuzzleMajor === 2" />
            <TopMenuV1 :kuzzle-major="kuzzleMajor" v-else />
          </div>
        </div>

        <!-- Search interface -->
        <div class="md-header-nav__search-container">
          <SearchBox :options="searchboxOptions" />
        </div>

        <div class="divider"></div>
        <button class="btnCta"><a href="https://kuzzle.io" target="_blank">Discover Kuzzle</a></button>

        <!-- Repository containing source -->
        <!-- <div class="md-flex__cell md-flex__cell--shrink">
              <div class="md-header-nav__source">
                  {% include "partials/source.html" %}
              </div>
        </div>-->
      </nav>
    </header>
  </div>
</template>

<script>
import TopMenu from './TopMenu.vue';
import TopMenuV1 from './TopMenuV1.vue';
import MajorVersionSelector from '../components/MajorVersionSelector.vue';

export default {
  components: {
    TopMenu,
    TopMenuV1,
    MajorVersionSelector,
  },
  name: 'Header',
  props: {
    kuzzleMajor: {
      type: Number,
      default: 2,
    },
  },
  computed: {
    searchboxOptions() {
      return {
        searchParameters: {
          facetFilters: [`version:${this.kuzzleMajor}`],
        },
      };
    },
  },
};
</script>

<style lang="scss">
.divider {
  height: 30px;
  width: 2px;
  background-color: rgba(255, 255, 255, 0.2);
  margin: 0 0.5rem;
}
</style>
