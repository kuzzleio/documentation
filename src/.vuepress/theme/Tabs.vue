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
                  'md-tabs__link--active': isLinkActive(`/core/${kuzzleMajor}/guides/`)
                }"
                :data-algolia-lvl="isLinkActive(`/core/${kuzzleMajor}/guides/`) ? '0' : ''"
                title="Guides"
                class="md-tabs__link"
              >Guides</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/api/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/core/${kuzzleMajor}/api/`)
                }"
                :data-algolia-lvl="isLinkActive(`/core/${kuzzleMajor}/api/`) ? '0' : ''"
                title="API"
                class="md-tabs__link"
              >API</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/sdk/?kuzzleMajor=${kuzzleMajor}`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/sdk/?kuzzleMajor=${kuzzleMajor}`)
                }"
                :data-algolia-lvl="isLinkActive(`/sdk/?kuzzleMajor=${kuzzleMajor}`) ? '0' : ''"
                title="SDK"
                class="md-tabs__link"
              >SDK</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/official-plugins/?kuzzleMajor=${kuzzleMajor}`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/official-plugins/?kuzzleMajor=${kuzzleMajor}`)
                }"
                :data-algolia-lvl="isLinkActive(`/official-plugins/?kuzzleMajor=${kuzzleMajor}`) ? '0' : ''"
                title="Plugins"
                class="md-tabs__link"
              >Plugins</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/how-to/?kuzzleMajor=${kuzzleMajor}`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/how-to/?kuzzleMajor=${kuzzleMajor}`)
                }"
                :data-algolia-lvl="isLinkActive(`/how-to/?kuzzleMajor=${kuzzleMajor}`) ? '0' : ''"
                title="How To"
                class="md-tabs__link"
              >How To</a>
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
                  'md-tabs__link--active': isLinkActive(`/core/${kuzzleMajor}/plugins/`)
                }"
                :data-algolia-lvl="isLinkActive(`/core/${kuzzleMajor}/plugins/`) ? '0' : ''"
                title="Write Plugins"
                class="md-tabs__link"
              >Write Plugins</a>
            </li>
            <li class="md-tabs__item">
              <a
                :href="`/core/${kuzzleMajor}/protocols/`"
                :class="{
                  'md-tabs__link--active': isLinkActive(`/core/${kuzzleMajor}/protocols/`)
                }"
                :data-algolia-lvl="isLinkActive(`/core/${kuzzleMajor}/protocols/`) ? '0' : ''"
                title="Write Protocols"
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
export default {
  props: ['kuzzleMajor'],
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
