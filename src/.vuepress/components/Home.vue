<template>
  <div>
    <h2>What is Kuzzle</h2>
    <p>
      Kuzzle is a ready-to-use, open-source, on-premises suite that allows you
      to build modern web, mobile and IoT applications in no time.
    </p>

    <h2>Learn more about Kuzzle now</h2>
    <div class="Tiles">
      <a
        :href="getPath('/guides/getting-started/run-kuzzle/')"
        class="Tiles-item medium"
      >
        <img
          :src="$withBase('/logos/rocket.svg')"
          alt="Running Kuzzle logo"
          class="Tiles-item-logo"
        />
        <div class="Tiles-item-name">Running Kuzzle</div>
      </a>
      <a
        :href="getPath('/framework/classes/backend/properties/')"
        class="Tiles-item medium"
      >
        <img
          :src="$withBase('/logos/write-code.svg')"
          alt="Running Kuzzle logo"
          class="Tiles-item-logo"
        />
        <div class="Tiles-item-name">Framework references</div>
      </a>
      <a :href="getPath('/api/payloads/request/')" class="Tiles-item medium">
        <img
          :src="$withBase('/logos/search.svg')"
          alt="Learn API logo"
          class="Tiles-item-logo"
        />
        <div class="Tiles-item-name">Learn API</div>
      </a>
      <a href="/sdk/" class="Tiles-item medium">
        <img :src="$withBase('/logos/brackets.svg')" alt="SDK logo" class="Tiles-item-logo" />
        <div class="Tiles-item-name">Find your SDK</div>
      </a>
      <a href="/official-plugins/" class="Tiles-item medium">
        <img
          :src="$withBase('/logos/plugin.svg')"
          alt="Plugins logo"
          class="Tiles-item-logo"
        />
        <div class="Tiles-item-name">Official plugins</div>
      </a>
      <a
        :href="getPath('/guides/main-concepts')"
        class="Tiles-item medium"
      >
        <img :src="$withBase('/logos/book.svg')" alt="Plugins logo" class="Tiles-item-logo" />
        <div class="Tiles-item-name">More guides</div>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  props: {
    kuzzleMajor: {
      type: Number
    }
  },
  methods: {
    getPath(path) {
      return `/core/${this.kuzzleMajor}${path}`;
    }
  }
};
</script>

<style></style>
