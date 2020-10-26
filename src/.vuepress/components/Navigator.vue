<template>
  <div class="Navigator">
    <span class="Navigator-prev" v-if="prev">
      <a :href="prev.url">◄ {{ prev.text }}</a>
    </span>
    <span class="Navigator-next" v-if="next">
      <a :href="next.url">{{ next.text }} ►</a>
    </span>
  </div>
</template>

<script>
export default {
  name: 'Navigator',
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
.Navigator {
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
