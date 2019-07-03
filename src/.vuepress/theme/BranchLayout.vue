<template>
  <div></div>
</template>

<script>
const { getFirstValidChild } = require('../util.js');

export default {
  mounted() {
    if (this.$page.frontmatter.type !== 'page') {
      this.$router.replace(getFirstValidChild(this.$page, this.$site.pages));
    }
  }
};
</script>

<style>
</style>
