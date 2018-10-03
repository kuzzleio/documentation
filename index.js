const _metalsmith = require('metalsmith');
const handlebars = require('handlebars');
const cheerio = require('cheerio');
const ymlRead = require('read-yaml');
const path = require('path');
const markdown = require('metalsmith-markdown');
const marked = require('marked');
const layouts = require('metalsmith-layouts');
const permalinks = require('metalsmith-permalinks');
const livereload = require('metalsmith-livereload');
const ancestry = require('metalsmith-ancestry');
const links = require('metalsmith-relative-links');
const hbtmd = require('metalsmith-hbt-md');
const sass = require('metalsmith-sass');
const hljs = require('metalsmith-metallic');
const inlineSVG = require('metalsmith-inline-svg');
const compress = require('metalsmith-gzip');
const optipng = require('metalsmith-optipng');
const sitemap = require('metalsmith-sitemap');
const htmlMin = require('metalsmith-html-minifier');
const algolia = require('metalsmith-algolia');
const metalsmithRedirect = require('metalsmith-redirect');
const concat = require('metalsmith-concat');
const uglify = require('metalsmith-uglify');
const serve = require('metalsmith-serve');
const watch = require('metalsmith-watch');
const color = require('colors/safe');
const discoverPartials = require('metalsmith-discover-partials');

// custom plugins
const snippetManager = require('./plugins/snippetManager');
const saveSrc = require('./plugins/save-src');
const anchors = require('./plugins/anchors');

// configuration
const versionsConfig = require('./config/versions');
const msDefaultOpts = require('./config/metalsmith');
const sdkVersions = JSON.stringify(ymlRead.sync(path.join(__dirname, './test/sdk-versions.yml'))).replace(/\s+/g, '');

// arguments
const argv = require('yargs').argv;
const manageArgs = require('./helpers/manageArgs');

// We override the default Markdown table renderer because
// we want tables to be wrapped into divs (for responsivity reasons).
const newMDRenderer = new marked.Renderer();
const tableRenderer = new marked.Renderer().table;

newMDRenderer.table = (header, body) => {
  return `<div class='table-wrapper'>${tableRenderer(header, body)}</div>`;
};

const ok = color.green('✔');
const nok = color.red('✗');
const options = manageArgs(argv, msDefaultOpts);
const ignored = [
  '**/**/sections/*',
  '**/**/snippets/*',
  '**/**/page.js.md',
  '**/**/page.go.md',
  '**/**/page.cpp.md',
  '**/**/page.java.md',
  '**/templates/*'
];

if (!options.dev.enabled) {
  ignored.push(...options.exclude.map(e => `**/${e}/**`));
}

function log(args) {
  console.log(color.magenta('[kuzzle-docs]'), args);
}

const redirectList = {
  //Now redirections for menus are set automatically
  //If you need others, add it here
};

for (const version of versionsConfig) {
  if (version.version_path === options.build.path) {
    log(`Using version ${color.bold(version.version_label)}`);

    options.github.repository = version.version_gh_repo;
    options.github.branch = version.version_gh_branch;
    options.algolia.index = version.algolia_index;
  }
}

options.algolia.fnFileParser = (file, data) => {
  const objects = [];
  const $ = cheerio.load(data.contents.toString(), {
    normalizeWhitespace: true
  });
  const content = $('.main-content');

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
};

handlebars.registerHelper(require('./helpers/handlebars.js'));

// Build site with metalsmith.
const metalsmith = _metalsmith(__dirname)
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
    is_dev: options.dev.enabled,
    sdkVersions: sdkVersions,
    exclude: options.exclude
  })
  .source('./src')
  .destination('./build' + options.build.path) // does not work with 'dist' folder ...
  .clean(true)
  .ignore(ignored)
  .use(saveSrc())
  .use((files, ms, done) => {
    setImmediate(done);
    for (const name of Object.keys(files)) {
      if (name.endsWith('.md') && files[name].order === undefined) {
        files[name].order = Number.MAX_SAFE_INTEGER;
      }
    }
  });

metalsmith
  .use(links())
  .use(ancestry({
    match: '**/*.md',
    sortBy: ['order', 'title']
  }));

if (options.dev.enabled) {
  console.log('= generating map sass files =');

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

metalsmith
  .use(hljs())
  .use(hbtmd(handlebars, {
    pattern: '**/*.md'
  }))
  .use(markdown({
    renderer: newMDRenderer
  }))
  .use((files, ms, done) => {
    for (const file in files) {
      if (file.endsWith('index.html')) {
        // Code Examples
        const codeExampleData = snippetManager.process(file, files[file]);
        files[file].contents = codeExampleData.fileContent;
        files[file].has_code_example = codeExampleData.has_code_example;

        // Anchors
        const anchorsData = anchors.process(file, files[file]);
        files[file].contents = anchorsData.fileContent;
        files[file].anchors = anchorsData.anchors;
      }
    }
    setImmediate(done);
  })
  .use(concat({
    files: 'assets/js/**/*.js',
    output: 'assets/js/main.js'
  }))
  .use(uglify({
    concat: {
      file: 'bundle.min.js',
      root: 'assets/js'
    },
    removeOriginal: true
  }))
  .use(permalinks({relative: false}));

metalsmith
  .use((files, ms, done) => {
    for (const file of Object.values(files)) {
      if (file.ancestry && file.ancestry.children) {
        const
          orderedPages = file.ancestry.children.sort((a,b) => {
            if (a.order === b.order) {
              if (a.title === b.title) {
                return 0;
              }

              return a.title < b.title ? -1 : 1;
            }

            return a.order - b.order;
          }),
          href = '/' + file.src.split('/').slice(0,-1).join('/'),
          redirect = '/' + orderedPages[0].src.split('/').slice(0,-1).join('/');

        redirectList[href] = redirect;
      }
    }
    setImmediate(done);
  })
  .use(metalsmithRedirect(redirectList))
  .use(discoverPartials({
    directory: 'src/templates/partials',
    pattern: /\.html$/
  }))
  .use(layouts({
    directory: 'src/templates'
  }));

if (options.algolia.privateKey) {
  log('Algolia indexing enabled');

  metalsmith
    .use(algolia({
      clearIndex: true,
      projectId: options.algolia.projectId,
      privateKey: options.algolia.privateKey,
      index: options.algolia.index,
      fileParser: options.algolia.fnFileParser
    }));
}

if (options.build.compress) {
  log('Compression enabled (build may take a while)');

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
          'src/assets/stylesheets/**/*': '**/*',
          'src/assets/**/*.js': '**/*',
          'src/**/*.md': '**/*',
          'src/templates/**/*': '**/*',
          'helpers/**/*': '**/*',
          '**/**/sections/*': '**/*',
          '**/**/snippets/*': '**/*'
        },
        livereload: true
      })
    );
}

log(`Building site in '${options.build.path}'`);
metalsmith.build((error, files) => {
  if (error) {
    log(nok + color.yellow(' Ooops...'));
    console.error(error);
    return;
  }
  log(ok + ' Build finished');
});
