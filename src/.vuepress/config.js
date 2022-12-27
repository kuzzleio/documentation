const webpack = require('webpack');

const siteDescription =
  'Complete Kuzzle Documentation: Guides, Framework, API, SDKs and officials plugins';
const versionString = require('./getVersionString');
const siteTitle = `Kuzzle Documentation `;
const base = process.env.SITE_BASE || '/';
const algoliaDefaultAppId = 'VF5HP4ZVDU';
const algoliaDefaultIndex = 'documentation-dev';
const algoliaDefaultSearchKey = 'de63216cd8d0116b2755916b9a38ae35';
const googleAnalyticsID = 'UA-67035328-7';
const sections = require('./sections.json');

console.log('base', base);

/**
 * vuepress [dev|build] <sourceDir> [options...]
 *
 * WARNING! This is extremely brittle, since passing options
 * as the second argument is also valid!
 */
const sourceDir = process.argv[3];

module.exports = {
  title: siteTitle,
  description: siteDescription,
  base,
  shouldPrefetch: () => false,
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
    ['link', { rel: 'canonical', href: 'https://docs.kuzzle.io/core/2/' }],
  ],

  markdown: {
    anchor: {
      permalink: true,
      permalinkBefore: false,
      permalinkClass: 'heading-anchor-link',
      permalinkSymbol: '#',
    },
    extendMarkdown: (md) => {
      md.use(require('./markdown/code-snippet'), {
        docsDir: sourceDir,
      });
      md.use(require('./markdown/copy-paste-snippet-btn'));
    },
  },
  configureWebpack: {
    plugins: [
      new webpack.DefinePlugin({
        GA_ID:
          JSON.stringify(process.env.GA_ID) ||
          JSON.stringify(googleAnalyticsID),
        'process.env.NODE_ENV': JSON.stringify(process.env.NODE_ENV),
        'process.env.RESET_APP_DATA_TIMER': JSON.stringify(
          process.env.RESET_APP_DATA_TIMER
        ),
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
      }),
    ],
  },
  plugins: [
    'vuepress-plugin-element-tabs',
    require('./meta-tags-plugin/index.js'),
    [require('./page-attributes/index.js'), { sections }],
    [
      require('vuepress-frontmatter-lint'),
      {
        dumpToFile: true,
        abortBuild: true,
        postProcessErrors: require('./validate-frontmatter/append-fixes'),
        specs: {
          type: {
            type: String,
            allowedValues: ['root', 'branch', 'page'],
            required: true,
          },
          order: {
            type: Number,
          },
          title: {
            type: String,
            required: true,
          },
          description: {
            type: String,
          },
          nosidebar: {
            type: Boolean,
          },
          code: {
            type: Boolean,
            required: true,
          },
          meta: {
            type: Array,
          },
        },
        exclude: [
          '/sdk/js/6/getting-started/vuejs/without-vuex/src/**/*',
          '/sdk/js/6/getting-started/vuejs/with-vuex/src/**/*',
        ],
      },
    ],
    [
      'container',
      {
        type: 'info',
        before: '<div class="alert alert-info">',
        after: '</div>',
      },
    ],
    [
      'container',
      {
        type: 'success',
        before: '<div class="alert alert-success">',
        after: '</div>',
      },
    ],
    [
      'container',
      {
        type: 'warning',
        before: '<div class="alert alert-warning">',
        after: '</div>',
      },
    ],
  ],
};
