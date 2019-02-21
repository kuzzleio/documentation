const BaseRunner = require('./baseRunner'),
  path = require('path'),
  // webpack is globally installed in the container
  webpack = require('/usr/local/lib/node_modules/webpack');

async function buildWithWebpack(renderedSnippetPath) {
  const config = {
    entry: path.resolve(__dirname, '..', 'webpack-assets', 'index.js'),
    output: {
      path: path.resolve(__dirname, '..', '..', 'bin', 'sdk-js', 'webpack'),
      filename: 'build.js'
    },
    module: {
      rules: [
        {
          test: /\.js$/,
          loader: 'babel-loader'
        }
      ]
    },
    devtool: 'eval-source-map',
    plugins: [
      new webpack.DefinePlugin({
        SNIPPET: JSON.stringify(renderedSnippetPath)
      })
    ]
  };

  return new Promise((resolve, reject) => {
    webpack(config, (err, stats) => {
      if (err) {
        if (err.details) {
          reject(err.details);
        }
        reject(err.stack || err);
      }
      const info = stats.toJson();

      if (stats.hasErrors()) {
        reject(info.errors);
      }

      if (stats.hasWarnings()) {
        resolve(info.warnings);
      }
      resolve(true);
    });
  });
}

module.exports = class WebRunner extends BaseRunner {
  constructor(sdk) {
    super(sdk);

    this.lintConfig = path.join(__dirname, '../../linters/eslint.json');
    this.lintCommand = './node_modules/.bin/eslint';
    this.lintOptions = ['-c', this.lintConfig];
    this.puppeteerPath = path.join(__dirname, '../scripts/puppeteer.js');
    this.indexHtmlPath = path.join(
      __dirname,
      '..',
      'webpack-assets',
      'index.html'
    );
    this.ext = 'js';
  }

  async runSnippet(snippet) {
    await buildWithWebpack(snippet.renderedSnippetPath);

    this.snippetCommand = `node ${this.puppeteerPath} ${this.indexHtmlPath}`;
    await super.runSnippet(snippet);
  }

  async lint(snippet) {
    await super.lint(
      snippet,
      this.lintOptions.concat(snippet.renderedSnippetPath)
    );
  }
};
