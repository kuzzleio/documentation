<template>
  <!-- Tabs with outline -->
  <nav class="md-tabs" data-md-component="tabs">
    <div class="md-tabs__inner md-grid">
      <ul class="md-tabs__list">
        <li class="md-tabs__group">
          <p class="md-tabs__group-name">Use</p>
          <ul class="md-tabs__group-items">
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/guides/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/guides/`
                  ),
                }"
                title="Guides"
                class="md-tabs__link"
                >Guides</a
              >
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/api/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/api/`
                  ),
                }"
                title="API"
                class="md-tabs__link"
                >API</a
              >
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/sdk/?${versionQueryKey}=${kuzzleMajor}`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/sdk/`),
                }"
                title="SDK"
                class="md-tabs__link"
                >SDK</a
              >
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/official-plugins/?${versionQueryKey}=${kuzzleMajor}`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/official-plugins/`),
                }"
                title="Plugins"
                class="md-tabs__link"
                >Plugins</a
              >
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/how-to/?${versionQueryKey}=${kuzzleMajor}`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/how-to/`),
                }"
                title="How To"
                class="md-tabs__link"
                >How To</a
              >
            </li>
          </ul>
        </li>
        <li class="md-tabs__group">
          <p class="md-tabs__group-name">Extend</p>
          <ul class="md-tabs__group-items">
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/plugins/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/plugins/`
                  ),
                }"
                title="Write Plugins"
                class="md-tabs__link"
                >Write Plugins</a
              >
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/protocols/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(
                    `/core/${kuzzleMajor}/protocols/`
                  ),
                }"
                title="Write Protocols"
                class="md-tabs__link"
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
import { VERSION_QUERY_KEY } from '../helpers';
export default {
  props: {
    kuzzleMajor: {
      type: Number,
      default: 2,
    },
  },
  computed: {
    versionQueryKey() {
      return VERSION_QUERY_KEY;
    },
  },
  methods: {
    isLinkActive(linkPath) {
      if (!this.$page.fullPath) {
        return false;
      }
      return this.$page.fullPath.startsWith(linkPath);
    },
  },
};
</script>

<style></style>
