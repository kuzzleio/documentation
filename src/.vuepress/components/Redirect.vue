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
    this.$router.push(this.to);
  },
};
</script>
