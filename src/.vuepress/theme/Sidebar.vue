<template>
  <div
    class="md-sidebar md-sidebar--primary"
    :class="{'md-sidebar--open': sidebarOpen}"
    data-md-component="navigation"
  >
    <div class="md-sidebar__scrollwrap" ref="scrollwrap">
      <div class="md-sidebar__inner">
        <nav class="md-nav md-nav--primary" data-md-level="0">
          <label class="md-nav__title md-nav__title--site mobile-only" for="drawer">
            <span class="md-nav__button md-logo">
              <img src="/logo-min.png" width="48" height="48">
            </span>
            <span>Kuzzle Documentation</span>
          </label>
          <TabsMobile/>
          <SDKSelector v-if="$route.path.match(/^\/sdk\//)" :items="sdkList"/>
          <!-- Render item list -->
          <ul class="md-nav__list" data-md-scrollfix>
            <div v-for="item__1 in getPageChildren(root)">
              <li class="md-nav__separator">{{item__1.frontmatter.title}}</li>

              <div v-for="item__2 in getPageChildren(item__1)">
                <li class="md-nav__item">
                  <router-link
                    class="md-nav__link"
                    :class="{'md-nav__link--active': $page.path === item__2.path, 'md-nav__item--code': item__2.frontmatter.code == true}"
                    :to="{path: getFirstValidChild(item__2).path}"
                  >
                    <div v-if="getPageChildren(item__2).length">
                      <i
                        v-if="$page.path.includes(item__2.path)"
                        class="fa fa-caret-down"
                        aria-hidden="true"
                      ></i>
                      <i v-else class="fa fa-caret-right" aria-hidden="true"></i>
                      <span>{{item__2.title}}</span>
                    </div>
                    <div v-else>
                      <span class="no_arrow">{{item__2.title}}</span>
                    </div>
                  </router-link>
                </li>

                <ul
                  v-if="$page.path.includes(item__2.path) && getPageChildren(item__2).length"
                  class="md-nav__list sub-menu"
                >
                  <div v-for="item__3 of getPageChildren(item__2)" class="md-nav__item">
                    <li v-if="$page.path === item__3.path">
                      <router-link
                        class="md-nav__link--active"
                        :class="{'md-nav__item--code': item__3.frontmatter.code}"
                        :to="{path: item__3.path}"
                        :title="item__3.title"
                      >
                        <span class="no_arrow">{{item__3.title}}</span>
                      </router-link>
                    </li>
                    <li v-else>
                      <router-link
                        :to="{path: item__3.path}"
                        :title="item__3.title"
                        class="md-nav__link"
                        :class="{'md-nav__item--code': item__3.frontmatter.code}"
                      >
                        <span class="no_arrow">{{item__3.title}}</span>
                      </router-link>
                    </li>
                  </div>
                </ul>
              </div>
            </div>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
import TabsMobile from './TabsMobile.vue';
import { getPageChildren, getFirstValidChild, findRootNode } from '../util.js';
import sdkList from '../sdk.json';

export default {
  data() {
    return {
      sdkList
    }
  },
  components: {
    TabsMobile
  },
  props: {
    sidebarOpen: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    root() {
      return findRootNode(this.$page, this.$site.pages);
    }
  },
  methods: {
    getPageChildren(page) {
      return getPageChildren(page, this.$site.pages);
    },
    getFirstValidChild(page) {
      return getFirstValidChild(page, this.$site.pages);
    },
    /**
     * @param {Element} target
     */
    isInViewport(target) {
      const rect = target.getBoundingClientRect();

      return (
        rect.bottom > 0 &&
        rect.right > 0 &&
        rect.left <
          (window.innerWidth || document.documentElement.clientWidth) &&
        rect.top < (window.innerHeight || document.documentElement.clientHeight)
      );
    }
  },
  mounted() {
    const activeLink = this.$el.querySelector('.md-nav__link--active');
    // !Dis is a ugli ack
    // If you find a better way to determine when the page has finished rendering,
    // you're my hero.
    setTimeout(() => {
      if (activeLink && !this.isInViewport(activeLink)) {
        this.$refs.scrollwrap.scrollTop = activeLink.offsetTop - 50;
      }
    }, 500);
  }
};
</script>

<style>
</style>
