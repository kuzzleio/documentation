const webpack = require('webpack');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const path = require('path');

module.exports = {
  context: path.resolve(__dirname, '../src/assets/js/'),
  entry: './main.js',
  output: {
    path: path.resolve(__dirname, '../build/assets/js'),
    filename: 'bundle.min.js'
  },
  plugins: [
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
      algoliasearch: 'algoliasearch',
      select2: 'select2'
    }),
    new UglifyJsPlugin({sourceMap: true}),
  ],
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
