<template>
  <div>
    <HowToCategorie
      v-for="cat in categories"
      :key="cat"
      :categorie="cat"
      :howToList="howToListByCategories(cat)"
    />
  </div>
</template>

<script>
import { getItemLocalStorage } from '../util';
import howToList from '../howto.json';

export default {
  name: 'HowToIndex',
  data() {
    return {
      kuzzleMajor: '2',
    };
  },
  mounted() {
    this.kuzzleMajor = getItemLocalStorage('kuzzleMajor') || '2';
  },
  computed: {
    howToListByCategories() {
      return (categorie) => howToList[this.kuzzleMajor][categorie] || [];
    },
    categories() {
      return Object.keys(howToList[this.kuzzleMajor]);
    },
  },
};
</script>

<style>
</style>
