<template>
  <div>
    <nav v-if="headers.length" class="md-nav md-nav--secondary">
      <label class="md-nav__title" for="toc">Table of contents</label>
      <ul class="md-nav__list" data-md-scrollfix>
        <li class="md-nav__item">
          <RouteLink to="" class="md-nav__link">{{ page$.title }}</RouteLink>
        </li>
        <li v-for="header of headers" class="md-nav__item">
          <RouteLink :to="getPath(header)" :class="headerLinkClass(header)">{{ header.title }}</RouteLink>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import {
  usePageData,
  useSiteData,
} from 'vuepress/client';

import { resolveHeaders } from '../util';

export default {
  computed: {
    headers() {
      return resolveHeaders(this.page$)[0].children || [];
    },
    isLinkActive() {
      return (link) => {
        return link === this.$route.fullPath;
      };
    },
    headerLinkClass() {
      return (header) => {
        return {
          'md-nav__link': true,
          'md-nav__link--active': this.isLinkActive(this.getPath(header)),
        };
      };
    },
  },
  setup() {
    return {
      page$: usePageData(),
      site$: useSiteData(),
    };
  },
  methods: {
    getPath(header) {
      return `${header.path}`;
    }
  }
};
</script>

<style></style>
