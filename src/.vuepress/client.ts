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
  faStackOverflow,
  faTriangleExclamation,
  faXTwitter,
  faYoutube,
);

export default defineClientConfig({
  enhance({ app, router }) {
    // Register the FontAwesomeIcon component
    app.component('font-awesome-icon', FontAwesomeIcon);

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
