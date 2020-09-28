<template>
  <div
    class="selector selector-majorVersion"
    ref="selector"
    v-if="items.length"
  >
    <div class="selector-selectedItem" @click="toggleList()">
      <div class="selector-selectedItem-name">
        <span class="selector-selectedItem-name-wrapper">
          {{ selectedItem ? selectedItem.text : 'Kuzzle' }}
          <i class="fa fa-caret-down" aria-hidden="true"></i>
        </span>
      </div>
    </div>
    <ul
      :class="`selector-list selector-list-${showList ? 'opened' : 'closed'}`"
    >
      <li
        v-for="item in items"
        :key="item.value"
        class="selector-list-item"
        @click="toggleList()"
      >
        <a class="selector-list-item-link" :href="getHref(item.value)">
          <span class="selector-list-item-name">{{ item.text }}</span>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import { getItemLocalStorage, log } from '../util';

export default {
  name: 'MajorVersionSelector',
  props: {
    kuzzleMajor: {
      type: Number,
    },
  },
  data() {
    return {
      items: [
        { value: 1, text: 'Kuzzle v1' },
        { value: 2, text: 'Kuzzle v2' },
      ],
      showList: false,
    };
  },
  computed: {
    selectedItem() {
      if (this.is404) {
        return null;
      }
      return this.items.find((item) => item.value === this.kuzzleMajor);
    },
    is404() {
      return (
        this.$route.matched.length === 1 && this.$route.matched[0].path === '*'
      );
    },
  },
  methods: {
    toggleList() {
      this.showList = !this.showList;
    },
    onClickOutside(e) {
      const el = this.$refs.selector,
        target = e.target;

      if (el && el !== target && !el.contains(target)) {
        this.showList = false;
      }
    },
    onItemClick(item) {
      this.$emit('change', item.value);
    },
    getHref(major) {
      if (this.is404) {
        return `/?kuzzleMajor=${major}`;
      }
      if (!this.$page.currentSection) {
        return `?kuzzleMajor=${major}`;
      }
      // Find the possible candidates of the same (sub)section
      // that correspond to the new Kuzzle Major
      const candidates = this.$page.sectionList.filter((s) => {
        if (s.kuzzleMajor !== major) {
          return false;
        }

        if (s.section !== this.$page.currentSection.section) {
          return false;
        }

        if (
          this.$page.currentSection.subsection &&
          this.$page.currentSection.subsection !== s.subsection
        ) {
          return false;
        }

        if (this.$page.currentSection.name !== s.name) {
          return false;
        }

        return true;
      });

      // If there's no candidate, just redirect to home
      if (!candidates || candidates.length === 0) {
        return `/?kuzzleMajor=${major}`;
      }

      // If there's one candidate, redirect to its index page
      if (candidates.length === 1) {
        return candidates[0].path;
      }

      // if there's many candidate, choose the one with the
      // highest version number
      const hypestCandidate = candidates.reduce((acc, curr) => {
        if (!curr.version || curr.version < acc.version) {
          return acc;
        }
        if (curr.version >= acc.version) {
          return curr;
        }
      }, candidates[0]);

      return hypestCandidate.path;
    },
  },
  mounted() {
    document.addEventListener('click', this.onClickOutside);
  },
};
</script>

<style lang="sass" scoped></style>
