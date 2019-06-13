<template>
  <div class="selector" ref="selector">
    <div class="selector-selectedItem" @click="toggleList()">
      <img
        v-if="currentLanguage"
        class="selector-selectedItem-icon"
        :src="currentLanguage.icon"
        :alt="currentLanguage.language"
      >
      <span
        class="selector-selectedItem-name"
      >{{ currentLanguage ? currentLanguage.name : 'Select an SDK' }}</span>
      <i class="fa fa-caret-down" aria-hidden="true"></i>
    </div>
    <ul class="selector-list">
      <li
        class="selector-list-item"
        v-for="item in items"
        v-show="isListShowed"
        :key="item.language + item.version"
        @click="toggleList()"
      >
        <router-link
          class="selector-list-item-link"
          :to="{path: generateLink(`/sdk/${item.language}/${item.version}/`)}"
        >
          <img class="selector-list-item-icon" :src="item.icon" :alt="item.language">
          <span class="selector-list-item-name">{{ item.name }}</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
const { getValidLinkByRootPath } = require('../util.js');

export default {
  props: {
    items: Array
  },
  data() {
    return {
      isListShowed: false
    };
  },
  computed: {
    currentLanguage() {
      const language = this.$route.path.split('/')[2],
        version = this.$route.path.split('/')[3];

      const lang = this.items.find(el => {
        return el.language === language && el.version === version;
      });

      return lang || null;
    }
  },
  methods: {
    generateLink(path) {
      return getValidLinkByRootPath(path, this.$site.pages);
    },
    toggleList: function() {
      this.isListShowed = !this.isListShowed;
    },
    onDocumentClick: function(e) {
      const el = this.$refs.selector,
        target = e.target;

      if (el && el !== target && !el.contains(target)) {
        this.isListShowed = false;
      }
    }
  },
  mounted() {
    document.addEventListener('click', this.onDocumentClick);
  }
};
</script>

<style lang="sass" scoped>
.selector
  width: 80%
  height: 40px
  font-size: 15px
  margin-bottom: 16px
  cursor: pointer
  &-selectedItem
    border-bottom: 1px solid rgba(233,78,119,0.3)
    padding: 10px 10px 5px
    &-icon
      position: relative
      top: 4px
      width: 22px
      height: 22px
      margin-right: 8px
    i
      float: right
      top: 10px
      position: relative
      color: #55595c

.selector-list
  position: absolute
  width: 80%
  background-color: white
  box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12)
  padding: 0
  margin-top: 4px
  &-item
    width: 100%
    list-style-type: none
    &-link
      display: block
      width: 100%
      height: 100%
      padding: 12px
    &-icon
      position: relative
      top: 4px
      width: 22px
      height: 22px
      margin-right: 8px
    &:hover
      background-color: #ddd

</style>