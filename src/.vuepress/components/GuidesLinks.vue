<template>
  <div class="GuidesLinks">
    <span class="GuidesLinks-prev" v-if="prev">
      <RouteLink :to="prevUrl">◄ {{ prev.text }}</RouteLink>
    </span>
    <span class="GuidesLinks-next" v-if="next">
      <RouteLink :to="nextUrl">{{ next.text }} ►</RouteLink>
    </span>
  </div>
</template>

<script>
function formatUrl(url) {
  if (url === undefined) {
    return undefined;
  }

  if (!url.endsWith('/')) {
    url += '/';
  }

  return url;
}

export default {
  name: 'GuidesLinks',
  props: {
    next: {
      type: Object
    },
    prev: {
      type: Object
    }
  },
  computed: {
    prevUrl() {
      return formatUrl(this.prev?.url);
    },
    nextUrl() {
      return formatUrl(this.next?.url);
    },
  },
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
