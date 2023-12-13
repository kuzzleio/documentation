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
      })
      .catch((err) => {
        if (err) {
          console.log(err);
        }
      });
  },
};
</script>
