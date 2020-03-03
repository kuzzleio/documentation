<template>
  <div class="md-layout">
    <Header ref="header" />
    <div ref="container" class="md-container">
      <main class="md-main">
        <div class="md-main__inner md-grid" data-md-component="container">
          <!-- Main navigation -->
          <div
            class="md-sidebar md-sidebar--primary"
            data-md-component="navigation"
            ref="sidebar"
          >
            <div
              v-if="!$page.frontmatter.nosidebar"
              class="md-sidebar__scrollwrap"
            >
              <div class="md-sidebar__inner"></div>
            </div>
          </div>

          <!-- Main container -->
          <div class="md-content">
            <article class="md-content__inner md-typeset">
              <h1>404 Page not found</h1>
              <p>
                Sorry! The page you are looking for doesn't exist or has been
                moved. We are working to correct this. <br />
                Maybe you can use the search field on the right top.<br />
                Otherwise, here are some useful pages.
              </p>
              <ul>
                <li>
                  <a :href="getPath('/guides/getting-started/running-kuzzle/')">
                    Running Kuzzle
                  </a>
                </li>
                <li>
                  <a :href="getPath('/api/essentials/connecting-to-kuzzle/')">
                    Explore API
                  </a>
                </li>
                <li>
                  <a href="/sdk/"> Find your SDK </a>
                </li>
                <li>
                  <a :href="getPath('/guides/essentials/admin-console/')">
                    Admin Console
                  </a>
                </li>
                <li>
                  <a :href="getPath('/guides/essentials/configuration/')">
                    Configure Kuzzle
                  </a>
                </li>
                <li>
                  <a href="/official-plugins/"> Plugins </a>
                </li>
              </ul>
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
      <Footer ref="footer" />
    </div>
  </div>
</template>

<script>
import Header from './Header.vue';
import Footer from './Footer.vue';

import { getFirstValidChild, getNodeByPath } from '../util.js';
import { getItemLocalStorage } from '../util';

export default {
  data() {
    return {
      kuzzleMajor: '2'
    };
  },
  components: {
    Header,
    Footer
  },
  methods: {
    getPath(path) {
      return `/core/${this.kuzzleMajor}${path}`;
    },
    generateHomeLink(path) {
      const rootPage = getNodeByPath(path, this.$site.pages);
      return getFirstValidChild(rootPage, this.$site.pages).path;
    },
    setContainerPadding() {
      const padding = this.$refs.header.$el.querySelector('header')
        .offsetHeight;

      if (padding === null || typeof padding === 'undefined') {
        return;
      }

      this.$refs.container.style = `padding-top: ${padding}px;`;
    }
  },
  mounted() {
    this.setContainerPadding();
    this.kuzzleMajor = getItemLocalStorage('kuzzleMajor') || '2';
  }
};
</script>

<style src="./styles/main.scss" lang="scss"></style>
