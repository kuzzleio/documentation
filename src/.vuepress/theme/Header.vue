<template>
  <div>
    <input
      class="md-toggle"
      ref="searchTrigger"
      data-md-toggle="search"
      type="checkbox"
      id="search"
      autocomplete="off"
    />
    <header class="md-header" data-md-component="header">
      <!-- Top-level navigation -->
      <nav class="md-header-nav md-grid">
        <div class="md-flex">
          <!-- Link to home -->
          <div class="md-flex__cell md-flex__cell--shrink">
            <a href="/" class="md-header-nav__button md-logo">
              <img src="/logo-min.png" width="40" height="40" />
            </a>
          </div>
          <!-- Button to toggle drawer -->
          <div class="md-flex__cell md-flex__cell--shrink">
            <label
              class="md-icon md-icon--menu md-header-nav__button"
              for="drawer"
              @click="$emit('openSidebar')"
            ></label>
          </div>

          <!-- Header title -->
          <div class="md-flex__cell md-flex__cell--stretch">
            <div
              class="md-flex__ellipsis md-header-nav__title"
              data-md-component="title"
            >
              <span class="md-header-nav__topic">
                <MajorVersionSelector :kuzzle-major="kuzzleMajor" />
              </span>
            </div>
          </div>
          <div
            class="screen-only md-flex__cell md-flex__cell--stretch md-flex__cell--menu"
          >
            <div style="display: none">{{ debugInfo }}</div>
            <TopMenu :kuzzle-major="kuzzleMajor" v-if="kuzzleMajor === 2" />
            <TopMenuV1 :kuzzle-major="kuzzleMajor" v-else />
          </div>
          <!-- Button to open search dialogue -->
          <div class="md-flex__cell md-flex__cell--shrink">
            <label
              class="md-icon md-icon--search md-header-nav__button"
              for="search"
            ></label>
            <!-- Search interface -->
            <ClientOnly>
              <Search
                v-if="algoliaSearchKey && algoliaAppId && algoliaIndexName"
                :app-id="algoliaAppId"
                :api-key="algoliaSearchKey"
                :index-name="algoliaIndexName"
                @search::on="toggleSearchTrigger(true)"
                @search::off="toggleSearchTrigger(false)"
              />
            </ClientOnly>
          </div>

          <!-- Repository containing source -->
          <!-- <div class="md-flex__cell md-flex__cell--shrink">
                <div class="md-header-nav__source">
                    {% include "partials/source.html" %}
                </div>
          </div>-->
        </div>
      </nav>
    </header>
  </div>
</template>

<script>
import Search from './Search.vue';
import TopMenu from './TopMenu.vue';
import TopMenuV1 from './TopMenuV1.vue';
import MajorVersionSelector from '../components/MajorVersionSelector.vue';

import { setItemLocalStorage, getItemLocalStorage } from '../util';

export default {
  components: {
    Search,
    TopMenu,
    TopMenuV1,
    MajorVersionSelector
  },
  name: 'Header',
  props: {
    kuzzleMajor: {
      type: Number,
      default: 2
    }
  },
  data() {
    return {
      algoliaAppId: ALGOLIA_APP_ID,
      algoliaSearchKey: ALGOLIA_SEARCH_KEY,
      algoliaIndexName: ALGOLIA_INDEX
    };
  },
  computed: {
    debugInfo() {
      return JSON.stringify(
        {
          kuzzleMajor: this.kuzzleMajor
        },
        null,
        2
      );
    }
  },
  methods: {
    toggleSearchTrigger(toggle) {
      this.$refs.searchTrigger.checked = toggle;
    }
  }
};
</script>

<style></style>
