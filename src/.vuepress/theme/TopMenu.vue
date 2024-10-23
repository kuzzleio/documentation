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
                  ),
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
                  'topMenu__link--active': isLinkActive(`/sdk/`),
                }"
                title="SDKs"
                class="topMenu__link"
                >SDKs</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/official-plugins/v${kuzzleMajor}.html`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/official-plugins/`),
                }"
                title="Plugins"
                class="topMenu__link"
                >Plugins</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/paas-console/1`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/paas-console/1`),
                }"
                title="PaaS"
                class="topMenu__link"
                >PaaS</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/iot-platform/3`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/iot-platform/3`),
                }"
                title="IoT Platform"
                class="topMenu__link"
                >IoT Platform</a
              >
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { usePageData } from 'vuepress/client';

import { VERSION_QUERY_KEY, getCurrentVersion } from '../helpers';

export default {
  computed: {
    versionQueryKey() {
      return VERSION_QUERY_KEY;
    },
    debugInfo() {
      return JSON.stringify(
        {
          kuzzleMajor: this.kuzzleMajor,
        },
        null,
        2
      );
    },
    kuzzleMajor() {
      return getCurrentVersion(this.page$);
    },
    referencesItems() {
      const pathStart = `/core/${this.kuzzleMajor}`;

      return [
        { name: 'API', url: `${pathStart}/api/` },
        { name: 'Framework', url: `${pathStart}/framework/` },
      ];
    },
  },
  setup() {
    return { page$: usePageData() };
  },
  methods: {
    isLinkActive(linkPath) {
      if (!this.page$.fullPath) {
        return false;
      }
      return this.page$.fullPath.startsWith(linkPath);
    },
  },
};
</script>
