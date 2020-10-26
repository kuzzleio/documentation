<template>
  <div class="SectionNavigator">
    <span class="SectionNavigator-prev" v-if="prev">
      <a :href="prev.url">◄ {{ prev.text }}</a>
    </span>
    <span class="SectionNavigator-next" v-if="next">
      <a :href="next.url">{{ next.text }} ►</a>
    </span>
  </div>
</template>

<script>
export default {
  name: 'SectionNavigator',
  props: {
    next: {
      type: Object,
    },
    prev: {
      type: Object,
    },
  },
};
</script>

<style lang="scss">
.SectionNavigator {
  padding: 2em 0 2em 0;
  font-weight: 600;

  &-prev {
    float: left;
  }

  &-next {
    float: right;
  }
}
</style>
