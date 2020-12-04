<template>
  <div>
    <div class="Tiles">
      <a
        v-for="sdk in sdkList"
        :key="sdk.name + sdk.version"
        :href="sdk.path"
        class="Tiles-item"
      >
        <div class="ribbon" v-if="sdk.kuzzleMajor === 2 && sdk.deprecated"><span>Deprecated</span></div>
        <img :src="sdk.icon" :alt="`${sdk.name} logo`" class="Tiles-item-logo" />
        <div class="Tiles-item-name">{{ sdk.name }} v{{ sdk.version }}.x</div>
      </a>
    </div>
    <div v-if="kuzzleMajor === 1">
      <h3>Community SDKs</h3>
      <span>the SDKs handcrafted by the community:</span>

      <div class="Tiles">
        <a
          href="https://github.com/kuzzleio/kuzzle_dart"
          class="Tiles-item min"
        >
          <img src="/logos/dart.svg" alt="dart logo" class="Tiles-item-logo" />
          <div class="Tiles-item-name">Dart</div>
        </a>
        <a
          href="https://github.com/alexandrebouthinon/kuzzle-sdk-rust"
          class="Tiles-item min"
        >
          <img src="/logos/rust.svg" alt="rust logo" class="Tiles-item-logo" />
          <div class="Tiles-item-name">Rust</div>
        </a>
      </div>
      <span>
        Do not hesitate to contact us or come on our
        <a href="http://join.discord.kuzzle.io" target="_blank">Discord chat</a>
        to add your contribution to this list.
      </span>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'SDKIndex',
  props: {
    kuzzleMajor: {
      type: Number,
      required: true,
    },
  },
  computed: {
    sdkList() {
      return this.$page.sectionList.filter(
        (s) =>
          s.kuzzleMajor === this.kuzzleMajor &&
          s.section === 'sdk' &&
          s.subsection &&
          // If we are deploying to the master branch, exclude the
          // sections that are not released yet
          (BRANCH === 'master' ? s.released === true : true)
      ).sort(s => s.deprecated ? 1 : -1);

    },
  },
};
</script>

<style>
</style>
