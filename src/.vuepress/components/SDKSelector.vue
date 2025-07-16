<template>
  <div class="selector" ref="selector" v-if="items.length">
    <div class="selector-selectedItem" @click="toggleList()">
      <img
        v-if="currentItem?.icon"
        class="selector-selectedItem-icon"
        :src="$withBase(currentItem.icon)"
        :alt="currentItem.name"
      />
      <img
        v-else
        class="selector-selectedItem-icon"
        :src="$withBase('/logo-57x57.png')"
        alt="Kuzzle logo"
      />
      <span v-if="currentItem" class="selector-selectedItem-name"
        >{{ currentItem.name }} v{{ currentItem.version }}.x</span
      >
    </div>
    <ul
      :class="`selector-list selector-list-${showList ? 'opened' : 'closed'}`"
    >
      <li
        v-for="item in filteredItems"
        :key="`${item.name}.${item.version}`"
        @click="toggleList()"
      >
        <a
          :class="`selector-list-item-link ${
            item.subsection === 'api' ? 'api' : ''
          }`"
          :href="item.path"
        >
          <img
            v-if="item.subsection !== 'api'"
            class="selector-list-item-icon"
            :src="$withBase(item.icon)"
            :alt="item.name"
          />
          <span
            :class="`selector-list-item-name${
              item.subsection === 'api' ? '-api' : ''
            }`"
            >{{ item.name }} v{{ item.version }}.x</span
          >
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import { usePageData } from 'vuepress/client';

export default {
  name: 'SDKSelector',
  props: {
    items: { type: Array, required: true },
    kuzzleMajor: { type: Number, required: true },
  },
  data() {
    return {
      showList: false,
    };
  },
  computed: {
    currentText() {
      return this.currentItem ? this.currentItem.name : 'Select SDK or API';
    },
    filteredItems() {
      return this.items.filter(
        (item) => item !== this.currentItem && item.icon !== undefined
      );
    },
    currentItem() {
      return this.items.find((i) => this.page$.fullPath.startsWith(i.path));
    },
  },
  methods: {
    toggleList: function () {
      this.showList = !this.showList;
    },
    onClickOutside: function (e) {
      const el = this.$refs.selector,
        target = e.target;

      if (el && el !== target && !el.contains(target)) {
        this.showList = false;
      }
    },
  },
  setup() {
    return { page$: usePageData() };
  },
  mounted() {
    document.addEventListener('click', this.onClickOutside);
  },
};
</script>

<style lang="sass" scoped></style>
