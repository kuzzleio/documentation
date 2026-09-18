<template>
  <div class="nav-selector" ref="selector" v-if="items.length">
    <button
      type="button"
      class="nav-selector__button"
      :class="{ 'nav-selector__button--open': showList }"
      :aria-expanded="showList ? 'true' : 'false'"
      :aria-label="label"
      aria-haspopup="true"
      @click.stop="toggleList()"
      @keydown.esc="closeList()"
    >
      <span class="nav-selector__label">
        {{ selectedItem ? selectedItem.text : placeholder }}
      </span>
      <font-awesome-icon
        class="nav-selector__caret"
        icon="fa-solid fa-caret-down"
      />
    </button>
    <ul class="nav-selector__list" v-show="showList" role="menu">
      <li
        v-for="item in items"
        :key="item.id"
        class="nav-selector__item"
        role="none"
      >
        <NavLink
          class="nav-selector__link"
          :class="{
            'nav-selector__link--active':
              selectedItem && selectedItem.id === item.id,
          }"
          role="menuitem"
          :path="item.path"
          @click="closeList()"
        >
          {{ item.text }}
        </NavLink>
      </li>
    </ul>
  </div>
</template>

<script>
import { useRoute, useSiteData } from 'vuepress/client';

import { absolutePath, findEntry } from '../products';

export default {
  name: 'NavSelector',
  props: {
    // Entries to display, see src/.vuepress/products.ts
    items: {
      type: Array,
      required: true,
    },
    // Displayed when the current page belongs to none of the items
    placeholder: {
      type: String,
      default: 'Kuzzle',
    },
    // Accessible name of the dropdown button
    label: {
      type: String,
      default: 'Change section',
    },
  },
  setup() {
    return { route$: useRoute(), site$: useSiteData() };
  },
  data() {
    return {
      showList: false,
    };
  },
  computed: {
    selectedItem() {
      return findEntry(
        this.items,
        absolutePath(this.site$.base, this.route$.path)
      );
    },
  },
  methods: {
    toggleList() {
      this.showList = !this.showList;
    },
    closeList() {
      this.showList = false;
    },
    onClickOutside(e) {
      const el = this.$refs.selector;

      if (el && el !== e.target && !el.contains(e.target)) {
        this.closeList();
      }
    },
    onEscape(e) {
      if (e.key === 'Escape') {
        this.closeList();
      }
    },
  },
  mounted() {
    document.addEventListener('click', this.onClickOutside);
    document.addEventListener('keydown', this.onEscape);
  },
  unmounted() {
    document.removeEventListener('click', this.onClickOutside);
    document.removeEventListener('keydown', this.onEscape);
  },
};
</script>
