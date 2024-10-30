<template>
  <div>
    <details v-if="$slots.default">
      <summary><Badge type="warning" :text="`Available since ${version}`"/></summary>
      <slot></slot>
    </details>
    <Badge v-else type="warning" :text="`Available since ${version}`"/>
  </div>
</template>
<script>

import Badge from './Badge.vue';

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
