<template>
  <div>Redirecting...</div>
</template>

<script>
export default {
  name: 'redirect',
  props: {
    to: {
      type: String,
      required: true,
    },
  },
  mounted() {
    if (
      window &&
      window.location &&
      typeof this.to === 'string' &&
      this.to !== null
    ) {
      let path = this.to;
      if (!path.startsWith('/')) {
        path = '/' + path;
      }
      window.location.href = window.location.origin + path;
    } else {
      console.error('Redirect failed');
    }
  },
};
</script>
