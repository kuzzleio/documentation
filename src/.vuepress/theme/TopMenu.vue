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
                title="Guides"
                class="topMenu__link"
              >Guides</a>
            </li>
            <li class="topMenu__item">
              <!-- DROP-DOWN MENU ITEM HERE -->
              <a
                :href="`/core/${kuzzleMajor}/references/`"
                :class="{
                  'topMenu__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/api/`
                  )
                }"
                title="References"
                class="topMenu__link"
              >References</a>
            </li>
            
            <li class="topMenu__item">
              <a
                :href="`/sdk/v${kuzzleMajor}.html`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/sdk/`)
                }"
                title="SDKs"
                class="topMenu__link"
              >SDKs</a>
            </li>
            <li class="topMenu__item">
              <a
                :href="`/plugins/v${kuzzleMajor}.html`"
                :class="{
                  'topMenu__link--active': isLinkActive(`/plugins/`)
                }"
                title="Plugins"
                class="topMenu__link"
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
