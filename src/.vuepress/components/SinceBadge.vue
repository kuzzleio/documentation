<template>
  <div>
    <details v-if="this.$slots.default">
      <summary><Badge type="warn" :text="`Available since ${version}`"/></summary>
      <slot></slot>
    </details>
    <Badge v-else type="warn" :text="`Available since ${version}`"/>
  </div>
</template>
<script>

import Badge from '@vuepress/theme-default/global-components/Badge.vue';

export default {
  name: 'SinceBadge',
  components: {
    Badge
  },
  props: {
    version: {
      type: String,
      required: true
    }
  }
};
</script>

<style>
</style>
