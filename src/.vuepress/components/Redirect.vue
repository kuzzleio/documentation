<template>
  <div>Redirecting...</div>
</template>

<script>
import { nextTick, onMounted } from 'vue';
import { useRoute, useRouter, useRoutes, withBase } from 'vuepress/client';

/**
 * Router path targeted by `to`.
 *
 * `to` comes in two shapes: a router path (`/framework/types/x/`, what
 * RedirectToFirstChild passes) or a path relative to the current page
 * (`classes/backend/properties`, what the markdown pages use).
 */
const resolveTarget = (from, to) => {
  const path = to.startsWith('/')
    ? to
    : new URL(to, `http://redirect${from}`).pathname;

  // Directory routes end with a slash, but file routes (`/sdk/v2.html`) must
  // keep their exact spelling or they match nothing.
  const isFile = /\.[^/]+$/.test(path);

  return isFile || path.endsWith('/') ? path : `${path}/`;
};

export default {
  name: 'redirect',
  props: {
    to: {
      type: String,
      required: true,
    },
  },
  setup(props) {
    const route = useRoute();
    const router = useRouter();
    const routes = useRoutes();

    onMounted(async () => {
      await nextTick();

      if (typeof window === 'undefined') {
        return;
      }

      const target = resolveTarget(route.path, props.to);

      // Pages of another VuePress instance are unknown to this router and
      // need a full page load; the rest stays client-side.
      if (routes.value[target]) {
        router.replace(target);
        return;
      }

      window.location.replace(withBase(target));
    });

    return {};
  },
};
</script>
