import viteBundler from '@vuepress/bundler-vite';
import { activeHeaderLinksPlugin } from '@vuepress/plugin-active-header-links';
import { backToTopPlugin } from '@vuepress/plugin-back-to-top';
import { copyCodePlugin } from '@vuepress/plugin-copy-code';
import { docsearchPlugin } from '@vuepress/plugin-docsearch';
import { googleAnalyticsPlugin } from '@vuepress/plugin-google-analytics';
import { markdownContainerPlugin } from '@vuepress/plugin-markdown-container';
import { registerComponentsPlugin } from '@vuepress/plugin-register-components';
import { shikiPlugin } from '@vuepress/plugin-shiki';
import { sitemapPlugin } from '@vuepress/plugin-sitemap';
import { getDirname, path } from '@vuepress/utils';
import anchorPlugin from 'markdown-it-anchor';
import { nodePolyfills } from 'vite-plugin-node-polyfills';
import { searchForWorkspaceRoot } from 'vite'
import { UserConfig, defineUserConfig } from 'vuepress';

import { frontmatterInRouteMetaPlugin } from './frontmatter-in-route-meta';
import { DEFAULT_EXCLUDE, frontmatterLintPlugin } from './frontmatter-lint';
import { markdownPlugin } from './markdown';
import { metaTagsPlugin } from './meta-tags-plugin';
import { pageAttributesPlugin } from './page-attributes';
import { kuzdocTheme } from './theme';

import versionString from './getVersionString';
import sections from './sections.json' with { type: 'json' };

const siteDescription =
  'Complete Kuzzle Documentation: Guides, Framework, API, SDKs and officials plugins';
const siteTitle = `Kuzzle Documentation `;
const base = process.env.SITE_BASE || '/';
const algoliaDefaultAppId = 'P66D1BU6XZ';
const algoliaDefaultIndex = 'kuzzle';
const algoliaDefaultSearchKey = '0510e62bc2d4f4407392852228117ba0';
const googleAnalyticsID = 'G-RTVRMCCF00';
const hostname = process.env.HOSTNAME || 'https://docs.kuzzle.io';

const __dirname = getDirname(import.meta.url);

export default defineUserConfig({
  title: siteTitle,
  description: siteDescription,
  base: base as UserConfig['base'],
  shouldPrefetch: false,
  clientConfigFile: path.resolve(__dirname, './client.ts'),

  theme: kuzdocTheme(),
  bundler: viteBundler({
    viteOptions: {
      css: {
        preprocessorOptions: {
          scss: {
            api: 'modern-compiler',
          },
        },
      },
      define: {
        ALGOLIA_APP_ID:
          JSON.stringify(process.env.ALGOLIA_APP_ID) ||
          JSON.stringify(algoliaDefaultAppId),
        ALGOLIA_SEARCH_KEY:
          JSON.stringify(process.env.ALGOLIA_SEARCH_KEY) ||
          JSON.stringify(algoliaDefaultSearchKey),
        ALGOLIA_INDEX:
          JSON.stringify(process.env.ALGOLIA_INDEX) ||
          JSON.stringify(algoliaDefaultIndex),
        REPO_SLUG:
          JSON.stringify(process.env.TRAVIS_REPO_SLUG) ||
          JSON.stringify('kuzzleio/documentation'),
        BRANCH: JSON.stringify(process.env.TRAVIS_BRANCH),
      },
      plugins: [
        nodePolyfills({
          include: ['path'],
        }),
      ],
      resolve: {
        preserveSymlinks: true,
      },
      server: {
        fs: {
          allow: [
            searchForWorkspaceRoot(process.cwd()),
            __dirname,
          ],
        },
      },
      ssr: {
        // See: https://github.com/FortAwesome/vue-fontawesome/issues/394
        noExternal: ['@fortawesome/vue-fontawesome'],
      },
    },
  }),

  head: [
    [
      'meta',
      {
        name: 'Doc-Version',
        content: versionString,
      },
    ],
    [
      'meta',
      { 'http-equiv': 'Content-Type', content: 'text/html; charset=utf-8' },
    ],
    ['meta', { 'http-equiv': 'X-UA-Compatible', content: 'IE=edge,chrome=1' }],
    [
      'meta',
      {
        name: 'viewport',
        content: 'width=device-width, initial-scale=1, maximum-scale=1',
      },
    ],
    [
      'meta',
      {
        name: 'google-site-verification',
        content: 'luspUdq52gkUU0FFChQ2xmeXSs5HDafpARQ7fVXVBp4',
      },
    ],

    // -- Schema.org markup for Google+
    ['meta', { itemprop: 'name', content: siteTitle }],
    [
      'meta',
      { itemprop: 'image', content: 'https://docs.kuzzle.io/og-image.png' },
    ],

    // -- Twitter Card data
    ['meta', { name: 'twitter:card', value: 'summary' }],
    ['meta', { name: 'twitter:site', content: '@kuzzleio' }],
    ['meta', { name: 'twitter:title', content: siteTitle }],
    ['meta', { name: 'twitter:creator', content: '@kuzzleio' }],
    [
      'meta',
      {
        property: 'twitter:image',
        content: 'https://docs.kuzzle.io/og-image.png',
        alt: 'kuzzle logo',
      },
    ],

    // -- Open Graph data
    ['meta', { property: 'og:type', content: 'article' }],
    ['meta', { property: 'og:site_name', content: siteTitle }],
    [
      'meta',
      {
        property: 'og:image',
        content: 'https://docs.kuzzle.io/og-image.png',
      },
    ],
    // The following two fields don't seem to be meaningful
    // [
    //   'meta',
    //   {
    //     property: 'article:published_time',
    //     content: new Date().toISOString() // '{{dateToISO stats.ctime}}'
    //   }
    // ],
    // [
    //   'meta',
    //   {
    //     property: 'article:modified_time',
    //     content: '{{dateToISO stats.mtime}}'
    //   }
    // ],

    // -- Favicons
    [
      'meta',
      {
        name: 'msapplication-TileImage',
        content: '/favicon/mstile-144x144.png',
      },
    ],
    [
      'meta',
      {
        name: 'msapplication-square70x70logo',
        content: '/favicon/mstile-70x70.png',
      },
    ],
    [
      'meta',
      {
        name: 'msapplication-square150x150logo',
        content: '/favicon/mstile-150x150.png',
      },
    ],
    [
      'meta',
      {
        name: 'msapplication-square310x310logo',
        content: '/favicon/mstile-310x310.png',
      },
    ],
    [
      'meta',
      {
        name: 'msapplication-square310x150logo',
        content: '/favicon/mstile-310x150.png',
      },
    ],
    [
      'link',
      {
        rel: 'icon',
        type: 'image/png',
        href: '/favicon/favicon-196x196.png',
        sizes: '196x196',
      },
    ],
    [
      'link',
      {
        rel: 'icon',
        type: 'image/png',
        href: '/favicon/favicon-96x96.png',
        sizes: '96x96',
      },
    ],
    [
      'link',
      {
        rel: 'icon',
        type: 'image/png',
        href: '/favicon/favicon-32x32.png',
        sizes: '32x32',
      },
    ],
    [
      'link',
      {
        rel: 'icon',
        type: 'image/png',
        href: '/favicon/favicon-16x16.png',
        sizes: '16x16',
      },
    ],
    [
      'link',
      {
        rel: 'icon',
        type: 'image/png',
        href: '/favicon/favicon-128.png',
        sizes: '128',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-57x57.png',
        sizes: '57x57',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-114x114.png',
        sizes: '114x114',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-114x114.png',
        sizes: '114x114',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-72x72.png',
        sizes: '72x72',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-144x144.png',
        sizes: '144x144',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-60x60.png',
        sizes: '60x60',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-120x120.png',
        sizes: '120x120',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-76x76.png',
        sizes: '76x76',
      },
    ],
    [
      'link',
      {
        rel: 'apple-touch-icon-precomposed',
        href: '/favicon/apple-touch-icon-152x152.png',
        sizes: '152x152',
      },
    ],
    ['script', { id: 'hs-script-loader', defer: true, src: '//js.hs-scripts.com/3803374.js' }],
  ],

  markdown: {
    anchor: {
      permalink: anchorPlugin.permalink.linkInsideHeader({
        class: 'heading-anchor-link',
        symbol: '#',
      }),
    },
  },

  plugins: [
    frontmatterInRouteMetaPlugin(),
    pageAttributesPlugin({ sections }),
    markdownPlugin(),
    metaTagsPlugin(),
    frontmatterLintPlugin({
      dumpToFile: true,
      abortBuild: true,
      specs: {
        type: {
          type: 'string',
          allowedValues: ['root', 'branch', 'page'],
          required: true,
        },
        order: {
          type: 'number',
          required: false,
        },
        title: {
          type: 'string',
          required: true,
        },
        description: {
          type: 'string',
          required: false,
        },
        nosidebar: {
          type: 'boolean',
          required: false,
        },
        code: {
          type: 'boolean',
          required: true,
        },
        meta: {
          type: 'array',
          required: false,
        },
      },
      exclude: [
        ...DEFAULT_EXCLUDE,
        '/sdk/js/6/getting-started/vuejs/without-vuex/src/**/*',
        '/sdk/js/6/getting-started/vuejs/with-vuex/src/**/*',
      ],
    }),
    registerComponentsPlugin({
      componentsDir: path.resolve(__dirname, './components'),
    }),
    sitemapPlugin({
      hostname,
      devServer: true,
    }),
    activeHeaderLinksPlugin({
      headerLinkSelector: 'a.route-link',
      headerAnchorSelector: '.heading-anchor-link',
      offset: 110,
    }),
    docsearchPlugin({
      apiKey: JSON.stringify(process.env.ALGOLIA_SEARCH_KEY) || algoliaDefaultSearchKey,
      indexName: JSON.stringify(process.env.ALGOLIA_INDEX) || algoliaDefaultIndex,
      appId: JSON.stringify(process.env.ALGOLIA_APP_ID) || algoliaDefaultAppId,
    }),
    googleAnalyticsPlugin({
      id: JSON.stringify(process.env.GA_ID) || JSON.stringify(googleAnalyticsID),
    }),
    backToTopPlugin(),
    shikiPlugin({
      langs: ['js', 'ts', 'json', 'vue', 'md', 'sh', 'java', 'xml', 'groovy', 'kotlin', 'yaml', 'dart', 'csharp', 'dockerfile', 'http'],
      theme: 'catppuccin-macchiato',
    }),
    copyCodePlugin({
      selector: '.md-content div[class*="language-"] pre',
    }),
    markdownContainerPlugin({
      type: 'info',
      before: () => '<div class="alert alert-info"><font-awesome-icon icon="fa-solid fa-circle-info" /><div class="content">',
      after: () => '</div></div>',
    }),
    markdownContainerPlugin({
      type: 'success',
      before: () => '<div class="alert alert-success"><font-awesome-icon icon="fa-solid fa-circle-check" /><div class="content">',
      after: () => '</div></div>',
    }),
    markdownContainerPlugin({
      type: 'warning',
      before: () => '<div class="alert alert-warning"><font-awesome-icon icon="fa-solid fa-triangle-exclamation" /><div class="content">',
      after: () => '</div></div>',
    }),
  ],
});
