<template>
  <div class="md-nav__source mobile-only md-nav__mobile-color">
    <div v-if="$route.path.match('sdk-reference')" class="selector-container">
      <SDKSelector :items="sdkList" :kuzzleMajor="kuzzleMajor" />
    </div>
    <div>
      <a
        :href="`/core/${kuzzleMajor}/guides/`"
        title="Guides"
        class="md-source"
        data-md-state="done"
        @click="$emit('closeSidebar')"
      >
        <div class="md-source__repository">Guides</div>
      </a>
    </div>
    <div>
      <a
        :href="`/core/${kuzzleMajor}/api/`"
        title="API"
        class="md-source"
        data-md-state="done"
        @click="$emit('closeSidebar')"
      >
        <div class="md-source__repository">API</div>
      </a>
    </div>
    <div>
      <a
        :href="`/sdk/?kuzzleMajor=${kuzzleMajor}`"
        title="SDK"
        class="md-source"
        data-md-state="done"
        @click="$emit('closeSidebar')"
      >
        <div class="md-source__repository">SDK</div>
      </a>
    </div>
    <div>
      <a
        :href="`/official-plugins/?kuzzleMajor=${kuzzleMajor}`"
        title="Official Plugins"
        class="md-source"
        data-md-state="done"
        @click="$emit('closeSidebar')"
      >
        <div class="md-source__repository">Official Plugins</div>
      </a>
    </div>
    <div>
      <a
        :href="`/how-to/?kuzzleMajor=${kuzzleMajor}`"
        title="How-to"
        class="md-source"
        data-md-state="done"
        @click="$emit('closeSidebar')"
      >
        <div class="md-source__repository">How-to</div>
      </a>
    </div>
    <div>
      <a
        :href="`/core/${kuzzleMajor}/plugins/`"
        title="Write Plugins"
        class="md-source"
        data-md-state="done"
        @click="$emit('closeSidebar')"
      >
        <div class="md-source__repository">Write Plugins</div>
      </a>
    </div>
    <div>
      <a
        :href="`/core/${kuzzleMajor}/protocols/`"
        title="Write Protocols"
        class="md-source"
        data-md-state="done"
        @click="$emit('closeSidebar')"
      >
        <div class="md-source__repository">Write Protocols</div>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  props: ['kuzzleMajor'],
};
</script>

<style>
</style>
