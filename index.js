const Metalsmith = require('metalsmith');
const handlebars = require('handlebars');
const cheerio = require('cheerio');
const stripTags = require('striptags');
const wordCount = require('wordcount');

const markdown = require('metalsmith-markdown');
const marked = require('marked');
const layouts = require('metalsmith-layouts');
const permalinks = require('metalsmith-permalinks');
const debug = require('metalsmith-debug');
const changed = require('metalsmith-changed');
const livereload = require('metalsmith-livereload');
const ancestry = require('metalsmith-ancestry');
const links = require('metalsmith-relative-links');
const hbtmd = require('metalsmith-hbt-md');
const sass = require('metalsmith-sass');
const autoprefix = require('metalsmith-autoprefixer');
const linkcheck = require('metalsmith-linkcheck');
const hljs = require('metalsmith-metallic');
const inlineSVG = require('metalsmith-inline-svg');
const compress = require('metalsmith-gzip');
const optipng = require('metalsmith-optipng');
const sitemap = require('metalsmith-sitemap');
const htmlMin = require('metalsmith-html-minifier');
const algolia = require('metalsmith-algolia');
const jsPacker = require('metalsmith-js-packer');
const cssPacker = require('metalsmith-css-packer');
const redirect = require('metalsmith-redirect');


const metatoc = require('./helpers/metatoc');
const codeExample = require('./helpers/codeExample');
const sectionOverride = require('./helpers/sectionOverride');
const saveSrc = require('./helpers/save-src');
const anchors = require('./helpers/anchors');
const nodeStatic = require('node-static');
const serve = require('metalsmith-serve');
const watch = require('metalsmith-watch');
const open = require('open');
const fs = require('fs');
const color = require('colors/safe');

const versionsConfig = require('./versions.config.json');

const ok = color.green("✔")
const nok = color.red("✗")

function log(args) {
  console.log(color.magenta('[kuzzle-docs]'), args);
}

let options = {
  dev: {
    enabled: false,
    port: 8080,
    openBrowser: false,
    watch: false
  },
  build: {
    compress: false,
    checkLinks: false,
    host: '',
    path: '/'
  },
  algolia: {
    projectId: '4RFBRWISJR',
    index: '',
    publicKey: '6febf1ebe906bd82bce58d5a20ac6c1b',
    privateKey: undefined,
    fnFileParser: undefined
  },
  github: {
    repository: '',
    branch: ''
  }
};

if (process.argv.indexOf('--dev') > -1) {
  options.dev.enabled = true;
}

if (process.argv.indexOf('--open-browser') > -1) {
  options.dev.openBrowser = true;
}

if (process.argv.indexOf('--port') > -1) {
  options.dev.port = parseInt(process.argv[process.argv.indexOf('--port') + 1]);
}

if (process.argv.indexOf('--watch') > -1) {
  options.dev.watch = true;
}

if (process.argv.indexOf('--ckeck-links') > -1) {
  options.build.checkLinks = true;
}

if (process.argv.indexOf('--build-compress') > -1) {
  options.build.compress = true;
}

if (process.argv.indexOf('--build-path') > -1) {
  options.build.path = process.argv[process.argv.indexOf('--build-path') + 1];
}

if (process.argv.indexOf('--build-host') > -1) {
  options.build.host = process.argv[process.argv.indexOf('--build-host') + 1];
}

if (process.argv.indexOf('--algolia-private-key') > -1) {
  options.algolia.privateKey = process.argv[process.argv.indexOf('--algolia-private-key') + 1];
}

if (options.dev.enabled) {
  log(`Starting ${color.bold('development')} environment...`);
} else {
  log(`Starting ${color.bold('production')} build...`);
}

for (let config of versionsConfig) {
  if (config.version_path === options.build.path) {
    log(`Using version ${color.bold(config.version_label)}`);

    options.github.repository = config.version_gh_repo;
    options.github.branch = config.version_gh_branch;
    options.algolia.index = config.algolia_index;
  }
}

options.algolia.fnFileParser = (file, data) => {
  let objects = [];
  let $ = cheerio.load(data.contents.toString(), {
    normalizeWhitespace: true
  });
  let content = $('.main-content');

  // remove useless content
  $('.hljs', content).remove();
  $('blockquote', content).remove();
  $('.language-tab-selector', content).remove();
  $('h1, h2, h3, h4, h5, h6', content).remove();

  objects.push({
    objectID: data.path,
    title: data.title,
    description: data.description ? data.description : '',
    path: data.path,
    content: content.text(),
    parent: (data.ancestry.parent ? data.ancestry.parent.title : ''),
    firstMember: (data.ancestry.firstMember ? data.ancestry.firstMember.title : ''),
    toc: data.toc
  });

  return objects;
}

/**
 * Usefull handlebars helpers
 */
handlebars.registerHelper({
  not: function (v) {
    return !v;
  },
  eq: function (v1, v2) {
    return v1 === v2;
  },
  ne: function (v1, v2) {
    return v1 !== v2;
  },
  lt: function (v1, v2) {
    return v1 < v2;
  },
  gt: function (v1, v2) {
    return v1 > v2;
  },
  lte: function (v1, v2) {
    return v1 <= v2;
  },
  gte: function (v1, v2) {
    return v1 >= v2;
  },
  and: function (v1, v2) {
    return v1 && v2;
  },
  or: function (v1, v2) {
    return v1 || v2;
  },
  startwith: function (str, substr) {
    if (!str) {
      return false;
    }
    return str.startsWith(substr);
  },
  mstartwith: function () {
    var args = Array.prototype.slice.call(arguments);
    var str = args.shift();
    if (!str) {
      return false;
    }
    return args.reduce(function (found, currentStr) {
      return found || str.startsWith(currentStr);
    }, false);
  },
  endswith: function (str, substr) {
    return str.endsWith(substr);
  },
  firstDefinedOf: function (...args) {
    return args.find(a => a);
  },
  currentYear: function () {
    return new Date().getFullYear();
  },
  dateToISO: function (d) {
    if (d instanceof Date) {
      return d.toISOString();
    }

    return d;
  },
  wordsToTime: function (context) {
    // It seems that 75 words per minute is a fair value for technical material
    return Math.ceil(wordCount(stripTags(context.data.root.contents)) / 75)
  },
  since: version => `<p class="since">Since Kuzzle v${version}</p>`,
  deprecated: version => `<p class="deprecated">Deprecated since Kuzzle v${version}. This feature should not be used.</p>`
})

// Build site with metalsmith.

let metalsmith = Metalsmith(__dirname)
  .metadata({
    site_title: 'Kuzzle documentation',
    site_url: options.build.host,
    site_base_path: options.build.path,
    gh_repo: options.github.repository,
    gh_branch: options.github.branch,
    algolia_projectId: options.algolia.projectId,
    algolia_publicKey: options.algolia.publicKey,
    algolia_index: options.algolia.index,
    versions_config: versionsConfig,
    is_dev: options.dev.enabled
  })
  .source('./src')
  .destination('./build' + options.build.path) // does not work with 'dist' folder ...
  .clean(true)
  .use(saveSrc())
  .use((files, metalsmith, done) => {
    setImmediate(done);
    Object.keys(files).forEach(path => {
      if (path.endsWith('.md') && files[path].order === undefined) {
        files[path].order = Number.MAX_SAFE_INTEGER
      }
    });
  })
  .use((files, metalsmith, done) => {
    for (let file in files) {
      if (file.endsWith('.md')) {
        let codeExampleData = codeExample.process(file, files[file]);  
        files[file].contents = codeExampleData['fileContent'];
        files[file]['has_code_example'] = codeExampleData['has_code_example'];
      }
    }
    setImmediate(done);
  })
  .use((files, metalsmith, done) => {
    for (let file in files) {
      if (file.endsWith('.md')) {
        let sectionsData = sectionOverride.process(file, files[file]);
        files[file].contents = sectionsData['fileContent'];
        files[file]['has_section'] = sectionsData['has_section'];
        files[file]['sections'] = sectionsData['sections'];
      }
    }
    setImmediate(done);
  });

metalsmith
  .use(links())
  .use(ancestry({
    match: '**/*.md',
    sortBy: ['order', 'title']
  }));

if (options.dev.enabled) {
  console.log(`= generating map sass files =`);

  metalsmith
    .use(sass({
      sourceMap: true,
      sourceMapContents: true
    }));
}
else {
  metalsmith
    .use(sass({
      sourceMap: false,
      sourceMapContents: false
    }));
}

// We override the default Markdown table renderer because
// we want tables to be wrapped into divs (for responsivity reasons).
const newMDRenderer = new marked.Renderer();
const tableRenderer = new marked.Renderer().table;

newMDRenderer.table = function (header, body) {
  return `<div class='table-wrapper'>${tableRenderer(header, body)}</div>`;
}

metalsmith
  .use(hljs())
  .use(hbtmd(handlebars, {
    pattern: '**/*.md'
  }))
  .use(markdown({
    renderer: newMDRenderer
  }))
  .use((files, metalsmith, done) => {
    for (let file in files) {
      if (file.endsWith('.html')) {
        files[file]['anchors'] = anchors.process(file, files[file]);
      }
    }
    setImmediate(done);
  })
  .use(permalinks({
    // It is very important that this option is set to false.
    // Otherwise, the partial builds in dev mode are not effective,
    // since this plugin overwrites the processed files with their
    // old version.
    relative: false
  }))
  .use(redirect({
    '/': '/guide/overview',
    '/guide': '/guide/overview',
    '/guide/getting-started': '/guide/getting-started/your-first-hello-world/',
    '/guide/kuzzle-backend-setup': '/guide/kuzzle-backend-setup/setup-sh',
    '/guide/kuzzle-backend-guide': '/guide/kuzzle-backend-guide/architecture-overview/',
    '/guide/kuzzle-for-iot': '/guide/kuzzle-for-iot/getting-started/',
    '/guide/kuzzle-for-web': '/guide/kuzzle-for-web/getting-started/',
    '/guide/kuzzle-for-mobile': '/guide/kuzzle-for-mobile/getting-started/',
    '/guide/kuzzle-admin-console': '/guide/kuzzle-admin-console/getting-started/',
    '/api-documentation/': '/api-documentation/connecting-to-kuzzle/',
    '/sdk-reference/': 'essentials/',
    '/plugins-reference/': 'plugins-features/',
    '/elasticsearch-cookbook/': '/elasticsearch-cookbook/installation/',
    '/kuzzle-dsl/': '/kuzzle-dsl/essential/koncorde/',
    '/validation-reference/': '/validation-reference/schema/',
    '/kuzzle-events/': '/kuzzle-events/plugin-events/'
  }))
  .use(metatoc())
  // .use(languageTab())
  .use(layouts({
    directory: 'src/templates',
    engine: 'handlebars',
    partials: 'src/templates/partials',
    exposeConsolidate(r) {
      r.handlebars = handlebars
    }
  }));

// if (!options.dev.enabled) {
//   log(`CSS and JS packers enabled`);
//   metalsmith
//     .use(cssPacker({
//       siteRootPath: options.build.path,
//       inline: false,
//       exclude: ['partials/**/*', 'layouts/**/*']
//     }))
//     .use(jsPacker({
//       siteRootPath: options.build.path,
//       inline: false,
//       exclude: ['partials/**/*', 'layouts/**/*']
//     }))
// }
// metalsmith
  // .use(clickImage())
  // .use(logger());

if (options.algolia.privateKey) {
  log(`Algolia indexing enabled`);

  metalsmith
    .use(algolia({
      clearIndex: true,
      projectId: options.algolia.projectId,
      privateKey: options.algolia.privateKey,
      index: options.algolia.index,
      fileParser: options.algolia.fnFileParser
    }));
}

if (options.build.checkLinks) {
  log(`Checking dead links enabled`);

  metalsmith
    .use(linkcheck({
      verbose: true,
      timeout: 5,
      checkFile: '.linkcheck/.links_checked.json',
      ignoreFile: '.linkcheck/links_ignore.json',
      failFile: '.linkcheck/links_failed.json'
    }));
}

if (options.build.compress) {
  log(`Compression enabled (build may take a while)`);

  metalsmith
    .use(inlineSVG())
    .use(optipng({
      pattern: '**/*.png',
      options: ['-o7']
    }))
    .use(htmlMin())
    .use(compress())
    .use(sitemap({
      hostname: options.build.host,
      modifiedProperty: 'stats.mtime',
      omitIndex: true
    }));
}

if (options.dev.enabled) {
  metalsmith
    .use(serve({
      port: 3000,
      verbose: false,
      host: 'localhost'
    }))
    .use(
      watch({
        paths: {
          "src/assets/stylesheets/**/*": "assets/stylesheets/**/*",
          "src/**/*.md": "**/*.md",
          "src/partials/**/*": "**/*.md",
          "src/layouts/**/*": "**/*.md",
          "src/assets/**/*.js": true
        },
        livereload: true
      })
    )
}

log(`Building site in "${options.build.path}"`);
metalsmith.build((error, files) => {
  if (error) {
    log(nok + color.yellow(' Ooops...'))
    console.error(error)
    return;
  }
  log(ok + ' Build finished');
});
