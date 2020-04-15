<template>
  <div>
    <details v-if="this.$slots.default">
      <summary><Badge type="error" :text="`Deprecated since ${version}`"/></summary>
      <slot></slot>
    </details>
    <Badge v-else type="error" :text="`Deprecated since ${version}`"/>
  </div>
</template>

<script>
import Badge from '@vuepress/theme-default/global-components/Badge.vue';

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
