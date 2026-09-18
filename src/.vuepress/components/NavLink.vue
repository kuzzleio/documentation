<template>
  <RouteLink v-if="routePath" :to="routePath" v-bind="$attrs">
    <slot />
  </RouteLink>
  <a v-else :href="path" v-bind="$attrs">
    <slot />
  </a>
</template>

<script>
import { useRoutes, useSiteData } from 'vuepress/client';

import { routerPath } from '../products';

/**
 * Link to a site-wide path, staying in the SPA whenever possible.
 *
 * Each product is built as its own VuePress instance mounted on its own base,
 * so only the paths belonging to the current instance can be routed on the
 * client. Anything else falls back to an `<a>`, which reloads the page.
 */
export default {
  name: 'NavLink',
  inheritAttrs: false,
  props: {
    // Site-wide path, e.g. `/core/2/api/`
    path: {
      type: String,
      required: true,
    },
  },
  setup() {
    return { routes$: useRoutes(), site$: useSiteData() };
  },
  computed: {
    routePath() {
      const candidate = routerPath(this.site$.base, this.path);

      return candidate && this.routes$[candidate] ? candidate : null;
    },
  },
};
</script>
