<template>
  <!-- Tabs with outline -->
  <nav class="md-tabs" data-md-component="tabs">
    <div style="display: none">{{ `/core/${kuzzleMajor}/guides/` }}</div>
    <div class="md-tabs__inner md-grid">
      <ul class="md-tabs__list">
        <li class="md-tabs__group">
          <ul class="md-tabs__group-items">
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/guides/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/guides/`
                  )
                }"
                title="Guides"
                class="md-tabs__link"
              >Guides</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/tutorials/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/tutorials/`
                  )
                }"
                title="Tutorials"
                class="md-tabs__link"
              >Tutorials</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/api/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/api/`
                  )
                }"
                title="API"
                class="md-tabs__link"
              >API</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/framework/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/framework/`
                  )
                }"
                title="Framework"
                class="md-tabs__link"
              >Framework</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/sdks/v${kuzzleMajor}.html`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/sdks/`)
                }"
                title="SDKs"
                class="md-tabs__link"
              >SDKs</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/plugins/v${kuzzleMajor}.html`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/plugins/`)
                }"
                title="Plugins"
                class="md-tabs__link"
              >Plugins</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { VERSION_QUERY_KEY } from '../helpers';
import { getCurrentVersion } from '../helpers';

export default {
  computed: {
    versionQueryKey() {
      return VERSION_QUERY_KEY;
    },
    debugInfo() {
      return JSON.stringify(
        {
          kuzzleMajor: this.kuzzleMajor
        },
        null,
        2
      );
    },
    kuzzleMajor() {
      return getCurrentVersion(this.$page);
    }
  },
  methods: {
    isLinkActive(linkPath) {
      if (!this.$page.fullPath) {
        return false;
      }
      return this.$page.fullPath.startsWith(linkPath);
    }
  }
};
</script>

<style></style>
