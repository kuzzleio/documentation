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
        <img :src="$withBase(sdk.icon)" :alt="`${sdk.name} logo`" class="Tiles-item-logo" />
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
          <img :src="$withBase('/logos/dart.svg')" alt="dart logo" class="Tiles-item-logo" />
          <div class="Tiles-item-name">Dart</div>
        </a>
        <a
          href="https://github.com/alexandrebouthinon/kuzzle-sdk-rust"
          class="Tiles-item min"
        >
          <img :src="$withBase('/logos/rust.svg')" alt="rust logo" class="Tiles-item-logo" />
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
</template>

<script>
import { usePageData } from 'vuepress/client';

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
      const sdks = this.page$.sectionList
        .filter(s => s.kuzzleMajor === this.kuzzleMajor)
        .filter(s => s.section === 'sdk')
        .filter(s => s.subsection)
        .filter(s => (BRANCH === 'master' ? s.released === true : true));

      const deprecatedSdks = sdks
        .filter(s => s.deprecated)

      return sdks
        .filter(s => !s.deprecated)
        .concat(deprecatedSdks)
    },
  },
  setup() {
    return { page$: usePageData() };
  },
};
</script>

<style>
</style>
