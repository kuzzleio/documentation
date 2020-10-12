<template>
  <div class="selector" ref="selector" v-if="filteredItems.length">
    <div class="selector-selectedItem" @click="toggleList()">
      <img
        v-if="currentItem"
        class="selector-selectedItem-icon"
        :src="currentItem.icon"
        :alt="currentItem.language"
      />
      <img v-else class="selector-selectedItem-icon" src="/logo-57x57.png" alt="Kuzzle logo" />
      <span class="selector-selectedItem-name">{{ getCurrentSpan }}</span>
      <i class="fa fa-caret-down" aria-hidden="true"></i>
    </div>
    <ul
      :class="
        `selector-list selector-list-${isListShowed ? 'opened' : 'closed'}`
      "
    >
      <li
        v-for="item in filteredItems"
        :key="item.language + item.version"
        :class="getItemClass(item)"
        @click="toggleList()"
      >
        <a
          :class="
            `selector-list-item-link ${item.language === 'api' ? 'api' : ''}`
          "
          :href="generateLink(item)"
        >
          <img
            v-if="item.language !== 'api'"
            class="selector-list-item-icon"
            :src="item.icon"
            :alt="item.language"
          />
          <span
            :class="
              `selector-list-item-name${item.language === 'api' ? '-api' : ''}`
            "
          >{{ getSpan(item) }}</span>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import { getOldSDK } from '../util.js';

export default {
  name: 'SDKSelector',
  props: {
    items: Array,
    kuzzleMajor: Number,
  },
  data() {
    return {
      isListShowed: false,
    };
  },
  computed: {
    oldSDK() {
      return getOldSDK(this.items);
    },
    getCurrentSpan() {
      return this.currentItem ? this.currentItem.name : 'Select an SDK';
    },
    filteredItems() {
      return this.items.filter(
        (item) => !this.$route.path.includes(`${item.language}/${item.version}`)
      );
    },
    currentItem() {
      let currentItem
      if (this.$page.fullPath.match(/\/sdk\/[a-z\-]+\/\d+\//)) {
        const splitPath = this.$page.fullPath.split('/');
        // ... find out which one based on the URL path
        currentItem = this.items.find(
          (el) => el.version === splitPath[3] && el.language === splitPath[2]
        );
      } else {
        currentItem = this.items.find(el => el.language === 'api')
      }
      return currentItem;
    },
  },
  methods: {
    getItemClass(item) {
      let itemClass;
      if (item.language === 'api') {
        itemClass = '';
      } else if (this.generateLink(item)) {
        itemClass = 'selector-list-item';
      } else {
        itemClass = 'selector-list-item disabled';
      }

      return itemClass;
    },
    getSpan(item) {
      if (this.$route.path.match(/\/sdk\//) && item.language === 'api') {
        return 'See API doc';
      }
      return item.name;
    },
    generateLink(item) {
      let method = '';
      let path = '';
      // if (this.$route.path.includes('controllers')) {
      //   method = `controllers/${this.$route.path.split('controllers/')[1]}`;
      // }
      if (item.language === 'api') {
        path = `/core/${this.kuzzleMajor}/api/`;
      } else {
        path = `/sdk/${item.language}/${item.version}/`;
      }
      // if (!this.oldSDK.includes(`${item.language}${item.version}`)) {
      //   path += method;
      // }
      return path;
    },
    toggleList: function () {
      this.isListShowed = !this.isListShowed;
    },
    onDocumentClick: function (e) {
      const el = this.$refs.selector,
        target = e.target;

      if (el && el !== target && !el.contains(target)) {
        this.isListShowed = false;
      }
    },
  },
  mounted() {
    document.addEventListener('click', this.onDocumentClick);
  },
};
</script>

<style lang="sass" scoped></style>
