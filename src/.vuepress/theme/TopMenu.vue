<template>
  <nav class="topMenu" data-md-component="tabs">
    <div style="display: none">{{ `/core/${kuzzleMajor}/guides/` }}</div>
    <div class="topMenu__inner md-grid">
      <ul class="topMenu__list">
        <li class="topMenu__group">
          <ul class="topMenu__group-items">
            <li class="topMenu__item">
              <a
                :href="`/core/${kuzzleMajor}/guides/`"
                :class="{
                  'topMenu__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/guides/`
                  )
                }"
                title="Guide"
                class="topMenu__link"
                >Guide</a
              >
            </li>
            <li class="topMenu__item">
              <MenuDropdown
                class="topMenu__link"
                title="References"
                :items="referencesItems"
              />
            </li>
            <li class="topMenu__item">
              <a
                :href="`/sdk/v${kuzzleMajor}.html`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/sdk/`)
                }"
                title="SDKs"
                class="topMenu__link"
                >SDKs</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/plugins/v${kuzzleMajor}.html`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/plugins/`)
                }"
                title="Plugins"
                class="topMenu__link"
                >Plugins</a
              >
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { VERSION_QUERY_KEY, getCurrentVersion } from '../helpers';

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
    },
    referencesItems() {
      const pathStart = `/core/${this.kuzzleMajor}`;

      return [
        { name: 'API', url: `${pathStart}/api/` },
        { name: 'Framework', url: `${pathStart}/framework/` }
      ];
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
