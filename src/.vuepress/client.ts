import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { defineClientConfig } from 'vuepress/client';

import {
  faAngleLeft,
  faArrowUpRightFromSquare,
  faBars,
  faCaretDown,
  faCaretRight,
  faCircleCheck,
  faCircleInfo,
  faGlobe,
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
