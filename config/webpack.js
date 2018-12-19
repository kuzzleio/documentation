const
  webpack = require('webpack'),
  UglifyJsPlugin = require('uglifyjs-webpack-plugin'),
  path = require('path');

module.exports = (opts) => {
  const plugins = [
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
      algoliasearch: 'algoliasearch',
      select2: 'select2'
    })
  ];

  if (! opts.dev.enabled) {
    plugins.push(new UglifyJsPlugin({sourceMap: true}));
  }

  return {
    plugins,
    context: path.resolve(__dirname, '../src/assets/js/'),
    entry: './main.js',
    output: {
      path: path.resolve(__dirname, '../build/assets/js'),
      filename: 'bundle.min.js'
    },
    module: {
      loaders: [
        {
          test: /\.js$/,
          exclude: [/app\.js$/, /prism\.js$/, /node_modules/],
          loader: 'babel-loader',
          query: {
            presets: ['@babel/preset-env']
          }
        }
      ]
    },
    devtool: 'source-map'
  };
};
