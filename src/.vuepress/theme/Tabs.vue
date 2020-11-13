<template>
  <!-- Tabs with outline -->
  <nav class="md-tabs" data-md-component="tabs">
    <div style="display: none">
      {{ `/core/${kuzzleMajor}/guides/` }}
    </div>
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
                >Guides</a
              >
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/references/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/references/`
                  )
                }"
                title="References"
                class="md-tabs__link"
                >References</a
              >
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/sdk/v${kuzzleMajor}.html`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/sdk/`)
                }"
                title="SDK"
                class="md-tabs__link"
                >SDK</a
              >
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/official-plugins/v${kuzzleMajor}.html`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/official-plugins/`)
                }"
                title="Plugins"
                class="md-tabs__link"
                >Official plugins</a
              >
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
