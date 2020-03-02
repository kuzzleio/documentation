<template>
  <div>
    <p>Beware, this SDK is deprecated.</p>
    <Badge
      type="error"
      :text="`Deprecated since ${version}`"
      id="deprecatedBadge"
    />
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
#deprecatedBadge {
  font-size: 22px;
  vertical-align: middle;
}
</style>
