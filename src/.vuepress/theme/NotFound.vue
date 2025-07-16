<template>
  <div class="md-layout">
    <div
      class="overlay"
      :class="{ hidden: !sidebarOpen }"
      @click="closeSidebar"
    ></div>
    <Header ref="header" @openSidebar="openSidebar" />
    <div ref="container" class="md-container">
      <main class="md-main">
        <div class="md-main__inner md-grid" data-md-component="container">
          <!-- Main navigation -->

          <!-- Main container -->
          <div class="md-content">
            <article class="md-content__inner md-typeset">
              <h1>404 Page not found</h1>
              <p>
                The page you are looking for doesn't exist or has been moved.<br />
                Maybe you can try to do a search.<br />
                Otherwise, you could go to the home page.
              </p>
              <div class="cta-wrapper">
                <btnCta btnTextInside="Go to home" btnUrl="/" />
              </div>
              <p>
                If you believe this is the result of an error, please
                <a
                  href="https://github.com/kuzzleio/documentation/issues/new/choose"
                >
                  let our team know.
                </a>
                We will be very grateful.
              </p>
            </article>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import { usePageData } from 'vuepress/client';

import { getCurrentVersion } from '../helpers';
import Header from './Header.vue';
import btnCta from '../components/Cta.vue';

export default {
  components: {
    Header,
    btnCta,
  },
  data() {
    return {
      sidebarOpen: false
    };
  },
  computed: {
    kuzzleMajor() {
      return getCurrentVersion(this.page$);
    }
  },
  setup() {
    return { page$: usePageData() };
  },
  methods: {
    setContainerPadding() {
      const padding = this.$refs.header.$el.querySelector('header')
        .offsetHeight;
      if (padding === null || typeof padding === 'undefined') {
        return;
      }
      this.$refs.container.style = `padding-top: ${padding}px;`;
    },
    openSidebar() {
      this.sidebarOpen = true;
    },
    closeSidebar() {
      this.sidebarOpen = false;
    }
  },
  mounted() {
    this.setContainerPadding();
  }
};
</script>

<style lang="scss">
.cta-wrapper {
  padding: 10px 0;
}
</style>
