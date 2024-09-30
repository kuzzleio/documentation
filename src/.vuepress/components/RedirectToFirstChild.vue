<template>
  <Redirect v-if="firstChild" :to="firstChild.path" />
</template>

<script>
import {
  resolveRoute,
  usePageData,
  useRoutes,
} from 'vuepress/client';

import Redirect from './Redirect.vue';
import { getFirstValidChild } from '../util';

export default {
  name: 'RedirectToFirstChild',
  components: {
    Redirect
  },
  computed: {
    firstChild() {
      return getFirstValidChild(this.page$, this.pages);
    },
    pages() {
      return Object.keys(this.routes$).map(resolveRoute);
    },
  },
  setup() {
    return {
      page$: usePageData(),
      routes$: useRoutes()
    };
  },
};
</script>
