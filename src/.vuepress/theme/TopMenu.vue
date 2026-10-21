<template>
  <nav class="topMenu" data-md-component="tabs" v-if="groups.length">
    <div class="topMenu__inner md-grid">
      <ul class="topMenu__list">
        <template v-for="(group, index) in groups" :key="index">
          <li class="topMenu__separator" v-if="index > 0" aria-hidden="true" />
          <li class="topMenu__group">
            <ul class="topMenu__group-items">
              <li
                class="topMenu__item"
                v-for="section in group"
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
        </template>
      </ul>
    </div>
  </nav>
</template>

<script>
import { useRoute, useSiteData } from 'vuepress/client';

import { useCurrentProduct } from '../current-product';
import { absolutePath, findEntry, topMenuGroups } from '../products';

/**
 * Sections of the current product, in groups separated by a divider: the
 * product's own sections first, then the ones shared across products.
 * Renders nothing for a product without any.
 */
export default {
  name: 'TopMenu',
  setup() {
    return {
      route$: useRoute(),
      site$: useSiteData(),
      product$: useCurrentProduct(),
    };
  },
  computed: {
    path() {
      return absolutePath(this.site$.base, this.route$.path);
    },
    groups() {
      return topMenuGroups(this.product$);
    },
    currentSection() {
      return findEntry(this.groups.flat(), this.path);
    },
  },
};
</script>
