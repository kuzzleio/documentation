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
    this.$router
      .push(this.to)
      .then(() => {
        console.log(`Redirected to ${this.to}`);
        window.location.reload();
      })
      .catch((err) => {
        if (err) {
          // Nothing with the error
        }
      });
  },
};
</script>
