<template>
  <div class="selector" ref="selector" v-if="items.length">
    <div class="selector-selectedItem" @click="toggleList()">
      <img
        v-if="currentItem"
        class="selector-selectedItem-icon"
        :src="currentItem.icon"
        :alt="currentItem.name"
      />
      <img v-else class="selector-selectedItem-icon" src="/logo-57x57.png" alt="Kuzzle logo" />
      <span
        v-if="currentItem"
        class="selector-selectedItem-name"
      >{{ currentItem.name }} v{{currentItem.version}}.x</span>
      <i class="fa fa-caret-down" aria-hidden="true"></i>
    </div>
    <ul :class="
        `selector-list selector-list-${showList ? 'opened' : 'closed'}`
      ">
      <li v-for="item in filteredItems" :key="`${item.name}.${item.version}`" @click="toggleList()">
        <a
          :class="
            `selector-list-item-link ${item.subsection === 'api' ? 'api' : ''}`
          "
          :href="item.path"
        >
          <img
            v-if="item.subsection !== 'api'"
            class="selector-list-item-icon"
            :src="item.icon"
            :alt="item.name"
          />
          <span
            :class="
              `selector-list-item-name${item.subsection === 'api' ? '-api' : ''}`
            "
          >{{ item.name }} v{{item.version}}.x</span>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
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
      return this.items.filter((item) => item !== this.currentItem);
    },
    currentItem() {
      return this.items.find((i) => this.$route.path.startsWith(i.path));
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
  mounted() {
    document.addEventListener('click', this.onClickOutside);
  },
};
</script>

<style lang="sass" scoped></style>
