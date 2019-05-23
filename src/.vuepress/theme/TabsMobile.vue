<template>
  <div class="md-nav__source mobile-only">
    <div v-if="$route.path.match('sdk-reference')" class="selector-container">
      <SDKSelector :items="sdkList"/>
    </div>

    <a
      href="/guide"
      title="Guides"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">Guides</div>
    </a>
    <a
      href="/sdk-reference"
      title="SDKs"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">SDK</div>
    </a>
    <a href="/api" title="API" class="md-source" data-md-state="done" style="display:inline-block;">
      <div class="md-source__repository">API</div>
    </a>
    <a
      href="/plugins"
      title="Plugins"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">Plugins</div>
    </a>
    <a
      href="/protocols"
      title="Protocols"
      class="md-source"
      data-md-state="done"
      style="display:inline-block;"
    >
      <div class="md-source__repository">Protocols</div>
    </a>
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
