<template>
  <div class="md-layout">
    <div class="overlayLoading" v-if="isLoading" />
    <div class="overlay" :class="{ hidden: !sidebarOpen }" @click="closeSidebar"></div>
    <Header
      ref="header"
      :kuzzle-major="kuzzleMajor"
      @kuzzle-major-changed="changeKuzzleMajor"
      @openSidebar="openSidebar"
    />

    <div ref="container" class="md-container">
      <!-- Main container -->
      <main class="md-main">
        <div class="md-main__inner md-grid" data-md-component="container">
          <!-- Main navigation -->
          <Sidebar
            ref="sidebar"
            :kuzzleMajor="kuzzleMajor"
            :sidebarOpen="sidebarOpen"
            @closeSidebar="closeSidebar"
          />
          <!-- Table of contents -->
          <div ref="toc" class="md-sidebar md-sidebar--secondary" data-md-component="toc">
            <div class="md-sidebar__scrollwrap">
              <div class="md-sidebar__inner">
                <div v-if="sdkOrApiPage" class="selector-container">
                  <SDKSelector :items="sdkList" :kuzzleMajor="kuzzleMajor" />
                </div>
                <TOC />
              </div>
            </div>
          </div>
          <!-- Content -->

          <div class="md-content">
            <div>
              <WarningBanner v-if="showDeprecatedBanner">
                This SDK has been deprecated because of stability issues. It is not
                advised to use it in a production environment.
              </WarningBanner>
            </div>
            <article class="md-content__inner md-typeset">
              <Content />
            </article>
          </div>
        </div>
      </main>

      <Footer ref="footer" />
    </div>
  </div>
</template>

<script>
import Clipboard from 'clipboard';
import Header from './Header.vue';
import WarningBanner from '../components/WarningBanner.vue';
import Sidebar from './Sidebar.vue';
import TOC from './TOC.vue';
import Footer from './Footer.vue';

import transform from 'lodash/transform';
import sections from '../sections.json';

const {
  getFirstValidChild,
  setItemLocalStorage,
  getItemLocalStorage,
} = require('../util.js');

export default {
  components: {
    Header,
    Sidebar,
    TOC,
    WarningBanner,
    Footer,
  },
  data() {
    return {
      sidebarOpen: false,
      isLoading: true,
    };
  },
  computed: {
    kuzzleMajor() {
      if (!this.$page.currentSection) {
        if (!this.$route.query.kuzzleMajor) {
          return 2;
        } else {
          return parseInt(this.$route.query.kuzzleMajor);
        }
      }

      return this.$page.currentSection.kuzzleMajor;
    },
    sdkOrApiPage() {
      if (!this.$page.currentSection) {
        return false;
      }

      return (
        this.$page.currentSection.section === 'sdk' ||
        this.$page.currentSection.subsection === 'api'
      );
    },
    sdkList() {
      return this.sectionList.filter(
        (s) =>
          s.kuzzleMajor === this.kuzzleMajor &&
          (s.section === 'sdk' || s.subsection === 'api')
      );
    },
    sections() {
      return sections;
    },
    sectionList() {
      return transform(
        this.sections,
        (result, value, key) => {
          result.push({ ...value, path: key });
        },
        []
      );
    },
    showDeprecatedBanner() {
      if (!this.$page.currentSection || !this.$page.currentSection.deprecated) {
        return false;
      }

      return this.$page.currentSection.deprecated;
    },
  },
  methods: {
    changeKuzzleMajor(newMajor) {
      if (!this.currentSection) {
        return this.$router.push(`/?kuzzleMajor=${newMajor}`);
      }
      // Find the possible candidates of the same (sub)section
      // that correspond to the new Kuzzle Major
      const candidates = this.sectionList.filter((s) => {
        if (s.kuzzleMajor !== newMajor) {
          return false;
        }

        if (s.section !== this.$page.currentSection.section) {
          return false;
        }

        if (
          this.$page.currentSection.subsection &&
          this.$page.currentSection.subsection !== s.subsection
        ) {
          return false;
        }

        if (this.$page.currentSection.name !== s.name) {
          return false;
        }

        return true;
      });

      // If there's no candidate, just redirect to home
      if (!candidates || candidates.length === 0) {
        return this.$router.push(`/?kuzzleMajor=${newMajor}`);
      }

      // If there's one candidate, redirect to its index page
      if (candidates.length === 1) {
        return this.$router.push(candidates[0].path);
      }

      // if there's many candidate, choose the one with the
      // highest version number
      const hypestCandidate = candidates.reduce((acc, curr) => {
        if (!curr.version || curr.version < acc.version) {
          return acc;
        }
        if (curr.version >= acc.version) {
          return curr;
        }
      }, candidates[0]);

      return this.$router.push(hypestCandidate.path);
    },
    openSidebar() {
      this.sidebarOpen = true;
    },
    closeSidebar() {
      this.sidebarOpen = false;
    },
    onCodeCopied(action) {
      const message = action.trigger.parentElement.querySelector(
        '.md-clipboard__message'
      );
      if (!(message instanceof HTMLElement)) throw new ReferenceError();

      /* Clear selection and reset debounce logic */
      action.clearSelection();
      if (message.dataset.mdTimer)
        clearTimeout(parseInt(message.dataset.mdTimer, 10));

      /* Set message indicating success and show it */
      message.classList.add('md-clipboard__message--active');

      /* Hide message after two seconds */
      message.dataset.mdTimer = setTimeout(() => {
        message.classList.remove('md-clipboard__message--active');
        message.dataset.mdTimer = '';
      }, 2000).toString();

      this.$ga('send', 'event', 'snippet', 'copied', 'label', 1, {
        path: this.$route.path,
      });
    },
    computeContentHeight() {
      this.setContainerPadding();
      setTimeout(() => {
        this.computeSidebarHeight();
      }, 200);
    },
    setContainerPadding() {
      const padding = this.$refs.header.$el.querySelector('header')
        .offsetHeight;

      if (padding === null || typeof padding === 'undefined') {
        return;
      }

      this.$refs.container.style = `padding-top: ${padding}px;`;
    },
    computeSidebarHeight() {
      const sidebarTop = window
        .getComputedStyle(this.$refs.sidebar.$el)
        .top.replace('px', '');

      /**
       * * This helps to understand if we're on a mobile screen or not.
       *
       * On big screens, the Sidebar's top is the same as the container's
       * padding-top, while on smaller screens it's 0. On small screens the
       * height of the sidebar is 100% (this is set in the stylesheets) so
       * we don't want to change it.
       */
      if (parseInt(sidebarTop) <= 0) {
        this.$refs.sidebar.$el.style = `height: inherit`;
        return;
      }

      const topBoundary = this.$refs.header.$el.querySelector('header')
        .offsetHeight;

      if (topBoundary === null || typeof topBoundary === 'undefined') {
        return;
      }

      const visible = window.innerHeight - topBoundary;
      let sidebarHeight = visible - this.$refs.footer.$el.offsetHeight;

      if (this.$refs.container.offsetHeight > visible) {
        sidebarHeight = Math.min(
          visible,
          this.$refs.container.offsetHeight -
            this.$refs.footer.$el.offsetHeight -
            window.pageYOffset -
            topBoundary
        );
      }

      this.$refs.sidebar.$el.style = `height: ${sidebarHeight}px`;
      this.$refs.toc.style = `height: ${sidebarHeight}px`;
    },
  },
  mounted() {
    document.onreadystatechange = () => {
      if (document.readyState === 'complete') {
        this.isLoading = false;
      }
    };

    this.$router.afterEach(this.computeContentHeight);
    window.addEventListener('resize', this.computeContentHeight.bind(this));
    window.addEventListener('scroll', this.computeSidebarHeight.bind(this));

    // TODO condition isSupported()
    const copy = new Clipboard('.md-clipboard', {
      target: (trigger) => {
        return trigger.parentElement.nextElementSibling;
      },
    });

    copy.on('success', this.onCodeCopied);

    if (this.$page.frontmatter.type !== 'page') {
      this.$router.replace(getFirstValidChild(this.$page, this.$site.pages));
    }

    this.computeContentHeight();

    // this.kuzzleMajor = getItemLocalStorage('kuzzleMajor') || '2';
  },
};
</script>

<style src="prismjs/themes/prism-tomorrow.css"></style>
<style src="./styles/main.scss" lang="scss"></style>

<style lang="scss">
.md-layout {
  height: 100%;
}
</style>
