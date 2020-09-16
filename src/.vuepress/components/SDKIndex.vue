<template>
  <div class="Tiles">
    <a v-for="sdk in sdkList" :key="sdk.name + sdk.version" :href="sdk.path" class="Tiles-item">
      <img :src="sdk.icon" :alt="`${sdk.name} logo`" class="Tiles-item-logo" />
      <div class="Tiles-item-name">{{ sdk.name }}</div>
    </a>
  </div>
</template>

<script>
export default {
  name: 'SDKIndex',
  computed: {
    kuzzleMajor() {
      if (!this.$page.currentSection) {
        if (!this.$route.query.kuzzleMajor) {
          return 2;
        } else {
          return parseInt(this.$route.query.kuzzleMajor);
        }
      }

      return this.$page.currentSection.kuzzleMajor;
    },
    sdkList() {
      return this.$page.sectionList.filter(
        (s) => s.kuzzleMajor === this.kuzzleMajor && s.section === 'sdk'
      );
    },
  },
};
</script>

<style>
</style>
