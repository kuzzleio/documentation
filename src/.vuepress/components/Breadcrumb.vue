<template>
  <nav v-if="trail.length > 1" class="breadcrumb" aria-label="Breadcrumb">
    <ol class="breadcrumb__list">
      <li v-for="(node, index) in trail" :key="node.path" class="breadcrumb__item">
        <RouteLink
          v-if="index < trail.length - 1"
          :to="node.path"
          class="breadcrumb__link"
        >
          {{ node.title }}
        </RouteLink>
        <span v-else class="breadcrumb__current" aria-current="page">
          {{ node.title }}
        </span>
      </li>
    </ol>
  </nav>
</template>

<script>
import { resolveRoute, usePageData, useRoutes } from 'vuepress/client';

import { shortTitle } from '../helpers';
import { getNodeByPath, getParentNode } from '../util';

/**
 * Trail of ancestors of the current page.
 *
 * Deep pages are often named after the last segment alone ("create"), which
 * says nothing about where the reader is; the sidebar shows it but scrolls
 * away. The tree is rebuilt from the routes, the same way the sidebar does.
 */
export default {
  name: 'Breadcrumb',
  setup() {
    return { page$: usePageData(), routes$: useRoutes() };
  },
  computed: {
    nodes() {
      return Object.keys(this.routes$).map((path) => {
        const route = resolveRoute(path);

        return { path: route.path, frontmatter: route.meta.frontmatter };
      });
    },
    trail() {
      const { nodes } = this;
      let node = getNodeByPath(this.page$.path, nodes);
      const trail = [];
      const seen = new Set();

      while (node && !seen.has(node.path)) {
        seen.add(node.path);
        trail.unshift({
          path: node.path,
          title: shortTitle(node.frontmatter?.title) || node.path,
        });

        if (node.frontmatter?.type === 'root' || node.path === '/') {
          break;
        }

        node = getParentNode(node, nodes);
      }

      return trail;
    },
  },
};
</script>
