<template>
  <div class="selector" ref="selector">
    <div class="selector-selectedItem" @click="toggleList()">
      <img class="selector-selectedItem-icon" :src="currentLanguage.icon" :alt="currentLanguage.language">
      <span class="selector-selectedItem-name">{{ currentLanguage.name }}</span>
      <i class="fa fa-caret-down" aria-hidden="true"></i>
    </div>
    <ul class="selector-list">
      <li
        class="selector-list-item"
        v-for="item in items"
        :key="item.language + item.version"
        v-show="isListShowed"
      >
        <a class="selector-list-item-link" :href="`/sdk-reference/${item.language}/${item.version}/`">
          <img class="selector-list-item-icon" :src="item.icon" :alt="item.language">
          <span class="selector-list-item-name">{{ item.name }}</span>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
module.exports = {
  props: ['items'],
  data() {
    return {
      isListShowed: false,
      currentLanguage: {}
    };
  },
  created () {
    document.addEventListener('click', this.onDocumentClick)
  },
  destroyed () {
    document.removeEventListener('click', this.onDocumentClick)
  },
  mounted(){
    this.setCurrentLanguage()
  },
  methods: {
    setCurrentLanguage: function() {
      const
        language = document.location.pathname.split('/')[2],
        version = document.location.pathname.split('/')[3];
        
      this.currentLanguage = this.items.find(el => {
        return el.language === language && el.version === version;
      });
    },
    toggleList: function() {
      this.isListShowed = !this.isListShowed;
    },
    onDocumentClick: function(e) {
      const 
        el = this.$refs.selector,
        target = e.target;
        
      if (el !== target && !el.contains(target)) {
        this.isListShowed = false;
      }
    },
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