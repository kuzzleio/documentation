<template>
  <div class="md-nav__source mobile-only">
    <div v-if="$route.path.match('sdk-reference')" class="selector-container">
      <SDKSelector :items="sdkList"/>
    </div>

    <router-link
      :to="{path: generateLink('/core/1/guides/')}"
      title="Guides"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">Guides</div>
    </router-link>
    <router-link
      :to="{path:generateLink('/core/1/api/')}"
      title="API"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">API</div>
    </router-link>
    <router-link
      :to="{path: '/sdk/'}"
      title="SDKs"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">SDK</div>
    </router-link>
    <router-link
      :to="{path: generateLink('/core/1/plugins/')}"
      title="Plugins"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">Plugins</div>
    </router-link>
    <router-link
      :to="{path: generateLink('/core/1/protocols/')}"
      title="Protocols"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">Protocols</div>
    </router-link>
  </div>
</template>

<script>
import sdkList from '../sdk.json';
export default {
  data() {
    return {
      sdkList
    };
  }
};
</script>

<style>
</style>
