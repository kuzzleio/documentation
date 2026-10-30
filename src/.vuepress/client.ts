import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { defineClientConfig } from 'vuepress/client';

import {
  faAngleLeft,
  faArrowRightFromBracket,
  faArrowUpRightFromSquare,
  faBars,
  faCaretDown,
  faCaretRight,
  faCircleCheck,
  faCircleInfo,
  faGlobe,
  faLock,
  faMagnifyingGlass,
  faMoon,
  faSun,
  faTriangleExclamation,
} from '@fortawesome/free-solid-svg-icons';

import {
  faDiscord,
  faGithub,
  faLinkedinIn,
  faStackOverflow,
  faXTwitter,
  faYoutube,
} from '@fortawesome/free-brands-svg-icons';

library.add(
  faAngleLeft,
  faArrowRightFromBracket,
  faArrowUpRightFromSquare,
  faBars,
  faCaretDown,
  faCaretRight,
  faCircleCheck,
  faCircleInfo,
  faDiscord,
  faGithub,
  faGlobe,
  faLinkedinIn,
  faLock,
  faMagnifyingGlass,
  faMoon,
  faStackOverflow,
  faSun,
  faTriangleExclamation,
  faXTwitter,
  faYoutube,
);

const HUBSPOT_SRC = 'https://js.hs-scripts.com/3803374.js';

/**
 * Load the HubSpot tracker once the page is idle.
 *
 * It costs ~2.3s of main thread on a mid-range device, and `defer` only pushes
 * that cost to the end of parsing: it still lands before the page is
 * interactive. Waiting for `load` keeps it out of the critical path.
 */
const loadHubspot = () => {
  if (
    typeof document === 'undefined' ||
    document.getElementById('hs-script-loader')
  ) {
    return;
  }

  const script = document.createElement('script');

  script.id = 'hs-script-loader';
  script.async = true;
  script.src = HUBSPOT_SRC;
  document.head.appendChild(script);
};

const whenIdle = (cb: () => void) => {
  if (typeof window === 'undefined') {
    return;
  }

  if ('requestIdleCallback' in window) {
    window.requestIdleCallback(cb, { timeout: 5000 });
  } else {
    window.setTimeout(cb, 2000);
  }
};

const STALE_RELOAD_KEY = 'kuzzle-docs:stale-build-reload';
const STALE_RELOAD_COOLDOWN_MS = 10_000;

/**
 * Tell whether we already reloaded for a stale build a moment ago.
 *
 * A reload that does not fix the failed import means the chunk is really
 * missing: reloading again would loop forever.
 */
const reloadedRecently = () => {
  try {
    const last = Number(window.sessionStorage.getItem(STALE_RELOAD_KEY));
    window.sessionStorage.setItem(STALE_RELOAD_KEY, String(Date.now()));

    return Date.now() - last < STALE_RELOAD_COOLDOWN_MS;
  } catch {
    return false;
  }
};

export default defineClientConfig({
  enhance({ app, router }) {
    // Register the FontAwesomeIcon component
    app.component('font-awesome-icon', FontAwesomeIcon);

    if (typeof window !== 'undefined') {
      if (document.readyState === 'complete') {
        whenIdle(loadHubspot);
      } else {
        window.addEventListener('load', () => whenIdle(loadHubspot), {
          once: true,
        });
      }
    }

    /**
     * Each deploy renames every page chunk and deletes the previous ones, so
     * a tab opened before a deploy fails to load the next page it navigates
     * to ("Importing a module script failed"). Vite reports these failures
     * with `vite:preloadError`; the navigation then fails, and we load the
     * target page from the server instead, which serves the current build.
     */
    if (typeof window !== 'undefined') {
      let staleBuild = false;

      window.addEventListener('vite:preloadError', () => {
        staleBuild = true;
      });

      router.onError((_error, to) => {
        if (!staleBuild || reloadedRecently()) {
          return;
        }

        window.location.assign(router.resolve(to).href);
      });
    }

    // Override the VuePress scroll behavior to set a custom top offset
    router.options.scrollBehavior = async (to, from, savedPosition) => {
      if (savedPosition) {
        return savedPosition;
      }

      if (to.hash.length > 1) {
        const elem = document.querySelector(to.hash);

        if (elem !== null) {
          const top = parseFloat(getComputedStyle(elem).scrollMarginTop);

          return {
            el: to.hash,
            behavior: 'smooth',
            top,
          };
        }
      }

      const behavior = to.path === from.path ? 'smooth' : 'instant';
      return { top: 0, left: 0, behavior };
    }
  },
});
