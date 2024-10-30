<template>
  <div>
    <details v-if="$slots.default">
      <summary><Badge type="danger" :text="`Deprecated since ${version}`"/></summary>
      <slot></slot>
    </details>
    <Badge v-else type="danger" :text="`Deprecated since ${version}`"/>
  </div>
</template>

<script>
import Badge from './Badge.vue';

export default {
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
