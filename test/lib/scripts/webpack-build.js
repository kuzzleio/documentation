const webpack = require('/home/luca/.nvm/versions/node/v10.12.0/lib/node_modules/webpack');
const path = require('path');

function buildWithWebpack(renderedSnippetPath) {
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

  webpack(config, (err, stats) => {
    if (err) {
      console.error(err.stack || err);
      if (err.details) {
        console.error(err.details);
      }
      return;
    }
    const info = stats.toJson();

    if (stats.hasErrors()) {
      console.error(info.errors);
    }

    if (stats.hasWarnings()) {
      console.warn(info.warnings);
    }
    console.log('Done.');
  });
}

buildWithWebpack(
  path.resolve(
    __dirname,
    '..',
    '..',
    '..',
    'src',
    'sdk-reference',
    'js',
    '6',
    'getting-started',
    'webpack',
    'snippets',
    'create.js'
  )
);
