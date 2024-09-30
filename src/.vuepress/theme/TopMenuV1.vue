<template>
  <!-- Tabs with outline -->
  <nav class="topMenu v1" data-md-component="tabs">
    <div style="display: none">
      {{ `/core/${kuzzleMajor}/guides/` }}
    </div>
    <div class="topMenu__inner md-grid">
      <ul class="topMenu__list">
        <li class="topMenu__group">
          <p class="topMenu__group-name">Use</p>
          <ul class="topMenu__group-items">
            <li class="topMenu__item">
              <a
                :href="`/core/${kuzzleMajor}/guides/`"
                :class="{
                  'topMenu__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/guides/`
                  )
                }"
                title="Guides"
                class="topMenu__link"
                >Guides</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/core/${kuzzleMajor}/api/`"
                :class="{
                  'topMenu__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/api/`
                  )
                }"
                title="API"
                class="topMenu__link"
                >API</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/sdk/v${kuzzleMajor}.html`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/sdk/`)
                }"
                title="SDK"
                class="topMenu__link"
                >SDK</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/official-plugins/v${kuzzleMajor}.html`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/official-plugins/`)
                }"
                title="Plugins"
                class="topMenu__link"
                >Plugins</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/how-to/v${kuzzleMajor}.html`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/how-to/`)
                }"
                title="How To"
                class="topMenu__link"
                >How To</a
              >
            </li>
          </ul>
        </li>
        <li class="topMenu__group">
          <p class="topMenu__group-name">Extend</p>
          <ul class="topMenu__group-items">
            <li class="topMenu__item">
              <a
                :href="`/core/${kuzzleMajor}/plugins/`"
                :class="{
                  'topMenu__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/plugins/`
                  )
                }"
                title="Write Plugins"
                class="topMenu__link"
                >Write Plugins</a
              >
            </li>
            <li class="topMenu__item">
              <a
                :href="`/core/${kuzzleMajor}/protocols/`"
                :class="{
                  'topMenu__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/protocols/`
                  )
                }"
                title="Write Protocols"
                class="topMenu__link"
                >Write Protocols</a
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
          kuzzleMajor: this.kuzzleMajor
        },
        null,
        2
      );
    },
    kuzzleMajor() {
      return getCurrentVersion(this.page$);
    }
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
    }
  }
};
</script>

<style></style>
