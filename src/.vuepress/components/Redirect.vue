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
  async created() {
    try {
      await this.$router.push(this.to);
    } catch (error) {
      console.error("Error while redirecting: ", error);
    }
  },
};
</script>
