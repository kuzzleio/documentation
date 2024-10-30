<template>
  <div class="md-layout">
    <AlgoliaTags :kuzzle-major="kuzzleMajor" />
    <div class="overlayLoading" v-if="isLoading" />
    <div
      class="overlay"
      :class="{ hidden: !sidebarOpen }"
      @click="closeSidebar"
    ></div>
    <Header
      ref="header"
      :kuzzle-major="kuzzleMajor"
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
            :sdkList="sdkList"
            :sidebarOpen="sidebarOpen"
            @closeSidebar="closeSidebar"
          />
          <!-- Table of contents -->
          <div
            ref="toc"
            class="md-sidebar md-sidebar--secondary"
            data-md-component="toc"
          >
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
            <major-version-deprecation
              v-if="kuzzleMajor !== kuzzleLatestMajor"
            />
            <div v-if="showDeprecatedBanner">
              <component
                v-if="deprecatedBannerComponent"
                :is="deprecatedBannerComponent"
              />
              <DeprecatedBanner v-else />
            </div>
            <closed-sources-banner v-if="isClosedSourcesSection" />
            <article class="md-content__inner md-typeset">
              <Content />
              <hr class="solid">
              <div class="edit-link" style="color: #4e6e8e;">
                <a target="_blank" rel="noopener noreferrer" :href="getGithubLink()">Edit this page on Github</a>
                <span class="sr-only">(opens new window)</span>
              </div>
            </article>
          </div>
        </div>
      </main>

      <Footer ref="footer" />
    </div>
  </div>
</template>

<script>
import { usePageData, useRouter } from 'vuepress/client';

import Header from './Header.vue';
import DeprecatedBanner from '../components/DeprecatedBanner.vue';
import Sidebar from './Sidebar.vue';
import TOC from './TOC.vue';
import Footer from './Footer.vue';
import { getCurrentVersion, DEFAULT_VERSION } from '../helpers';
import MajorVersionDeprecation from '../components/MajorVersionDeprecation.vue';
import ClosedSourcesBanner from '../components/ClosedSourcesBanner.vue';
import repositories from '../../../.repos/repositories.json';

export default {
  components: {
    Header,
    Sidebar,
    TOC,
    DeprecatedBanner,
    Footer,
    MajorVersionDeprecation,
    ClosedSourcesBanner
  },
  setup() {
    return {
      page$: usePageData(),
      router$: useRouter(),
    };
  },
  data() {
    return {
      sidebarOpen: false,
      isLoading: true,
      headerResizeObserver: undefined,
      removeRouterListener: undefined,
    };
  },
  computed: {
    kuzzleLatestMajor() {
      return DEFAULT_VERSION;
    },
    kuzzleMajor() {
      return getCurrentVersion(this.page$);
    },
    sdkOrApiPage() {
      if (!this.page$.currentSection) {
        return false;
      }

      return (
        (this.page$.currentSection.section === 'sdk' &&
          this.page$.currentSection.subsection) ||
        this.page$.currentSection.subsection === 'api'
      );
    },
    sdkList() {
      return this.page$.sectionList.filter(
        s =>
          s.kuzzleMajor === this.kuzzleMajor &&
          (s.section === 'sdk' || s.subsection === 'api') &&
          s.released
      );
    },
    showDeprecatedBanner() {
      if (!this.page$.currentSection || !this.page$.currentSection.deprecated) {
        return false;
      }

      return this.page$.currentSection.deprecated;
    },
    deprecatedBannerComponent() {
      return this.page$.currentSection.deprecatedBannerComponent || null;
    },
    isClosedSourcesSection() {
      if (
        !this.page$.currentSection ||
        !this.page$.currentSection.closedSources
      ) {
        return false;
      }

      return this.page$.currentSection.closedSources;
    },
  },
  methods: {
    getGithubLink () {
      const fullPath = this.page$.fullPath;
      const base = fullPath.replace(this.page$.regularPath, '');
      const relativePath = fullPath.replace(base, '');
      const repository = repositories.find(repo => repo.deploy_path.startsWith(base));

      if (! repository) {
        return;
      }

      const link = `${repository.url.replace('.git', '')}/blob/${repository.dev}/doc/${repository.doc_version}/${relativePath}index.md`;

      return link;
    },
    openSidebar() {
      this.sidebarOpen = true;
    },
    closeSidebar() {
      this.sidebarOpen = false;
    },
    computeContentHeight() {
      this.setContainerPadding();
      this.computeSidebarHeight();
    },
    setContainerPadding() {
      try {
        const padding = this.$refs.header.$el.querySelector('header')
          .offsetHeight;

        if (padding === null || typeof padding === 'undefined') {
          return;
        }

        this.$refs.container.style = `padding-top: ${padding}px;`;
      } catch (error) {
        return;
      }
    },
    computeSidebarHeight() {
      if (!this.$refs.sidebar) {
        return;
      }
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
    }
  },
  mounted() {
    // fix scroll to anchor on chrome https://github.com/vuejs/vuepress/issues/2558
    if (location.hash && location.hash !== '#') {
      const anchorLocation = decodeURIComponent(location.hash);
      const anchorElement = document.querySelector(anchorLocation);
      if (anchorElement && anchorElement.offsetTop)
        window.scrollTo(0, anchorElement.offsetTop);
    }

    this.isLoading = document.readyState !== 'complete';
    document.onreadystatechange = () => {
      if (document.readyState === 'complete') {
        this.isLoading = false;
      }
    };

    window.addEventListener('resize', this.computeContentHeight.bind(this));
    window.addEventListener('scroll', this.computeSidebarHeight.bind(this));

    this.headerResizeObserver = new ResizeObserver(
      this.computeContentHeight.bind(this)
    );
    this.headerResizeObserver.observe(this.$refs.header.$el.querySelector('header'));

    this.removeRouterListener = this.router$.afterEach(() => {
      this.$nextTick(() => {
        this.computeContentHeight();
      });
    });

    this.computeContentHeight();
  },
  beforeUnmount() {
    window.removeEventListener('resize', this.computeContentHeight.bind(this));
    window.removeEventListener('scroll', this.computeSidebarHeight.bind(this));

    this.headerResizeObserver.disconnect();
    this.removeRouterListener?.();
  }
};
</script>
