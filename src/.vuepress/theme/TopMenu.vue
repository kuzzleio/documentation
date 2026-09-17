<template>
  <nav class="topMenu" data-md-component="tabs">
    <div class="topMenu__inner md-grid">
      <ul class="topMenu__list">
        <li class="topMenu__group">
          <ul class="topMenu__group-items">
            <li
              class="topMenu__item"
              v-for="section in sections"
              :key="section.id"
            >
              <NavLink
                :path="section.path"
                :title="section.text"
                class="topMenu__link"
                :class="{
                  'topMenu__link--active':
                    currentSection && currentSection.id === section.id,
                }"
                >{{ section.text }}</NavLink
              >
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { useRoute, useSiteData } from 'vuepress/client';

import { BACKEND_SECTIONS, absolutePath, findEntry } from '../products';

export default {
  name: 'TopMenu',
  setup() {
    return { route$: useRoute(), site$: useSiteData() };
  },
  computed: {
    sections() {
      return BACKEND_SECTIONS;
    },
    currentSection() {
      return findEntry(
        BACKEND_SECTIONS,
        absolutePath(this.site$.base, this.route$.path)
      );
    },
  },
};
</script>
