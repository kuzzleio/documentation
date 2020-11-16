<template>
  <div class="GuidesLinks">
    <span class="GuidesLinks-prev" v-if="prev">
      <router-link :to="prev.url">◄ {{ prev.text }}</router-link>
    </span>
    <span class="GuidesLinks-next" v-if="next">
      <router-link :to="next.url">{{ next.text }} ►</router-link>
    </span>
  </div>
</template>

<script>
export default {
  name: 'GuidesLinks',
  props: {
    next: {
      type: Object
    },
    prev: {
      type: Object
    }
  }
};
</script>

<style lang="scss">
.GuidesLinks {
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
