<template>
  <div></div>
</template>

<script>
export default {
  name: 'RedirectBis',
  props: {
    to: {
      type: String,
      required: true
    }
  },
  mounted() {
    window.location.pathname = this.to;
  }
};
</script>
