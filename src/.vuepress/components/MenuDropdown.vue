<template>
  <a
    class="MenuDropdown"
    :class="{ active: isActive }"
    @mouseenter="isListVisible = true"
    @mouseleave="isListVisible = false"
  >
    <span class="MenuDropdown-title"
      >{{ title }} <i class="fa fa-caret-down"></i
    ></span>
    <ul class="MenuDropdown-list" :class="{ visible: isListVisible }">
      <li v-for="item in items" class="MenuDropdown-list-item" :key="item.name">
        <a
          class="MenuDropdown-list-item-link"
          :href="item.url"
          :class="{ active: isItemActive(item.url) }"
          >{{ item.name }}</a
        >
      </li>
    </ul>
  </a>
</template>

<script>
export default {
  props: {
    items: {
      type: Array,
      required: true,
      validator(items) {
        for (const item of items) {
          if (
            !Object.keys(item).includes('name') ||
            !Object.keys(item).includes('url')
          ) {
            return false;
          }
        }

        return true;
      }
    },
    title: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      isListVisible: false
    };
  },
  computed: {
    isActive() {
      let isActive = false;

      for (const item of this.items) {
        if (this.isItemActive(item.url)) {
          isActive = true;
        }
      }

      return isActive;
    }
  },
  methods: {
    isItemActive(itemUrl) {
      if (!this.$page || !this.$page.fullPath) {
        return false;
      }
      return this.$page.fullPath.startsWith(itemUrl);
    }
  }
};
</script>
