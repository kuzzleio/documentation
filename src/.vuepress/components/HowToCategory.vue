<template>
  <div>
    <h2>{{ categorie }}</h2>
    <div class="Tiles">
      <a v-for="howTo in howToList" :key="howTo.name" :href="howTo.path" class="Tiles-item">
        <img :src="$withBase(howTo.icon)" :alt="`${howTo.name} logo`" class="Tiles-item-logo" />
        <div class="Tiles-item-name">{{ howTo.name }}</div>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HowToCategory',
  props: {
    categorie: {
      type: String,
      required: true,
    },
    howToList: {
      type: Array,
      required: true,
    },
  },
};
</script>
