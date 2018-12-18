const 
  webpack = require('webpack'),
  UglifyJsPlugin = require('uglifyjs-webpack-plugin'),
  path = require('path'),
  VueLoaderPlugin = require('vue-loader/lib/plugin');

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
    }),
    new UglifyJsPlugin({sourceMap: true}),
    new VueLoaderPlugin()
  ],
  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: [/\app.js$/, /\prism.js$/, /node_modules/],
        loader: 'babel-loader',
        query: {
          presets: ['@babel/preset-env']
        }
      },
    ],
    rules: [
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },
      {
        test: /\.js$/,
        loader: 'babel-loader'
      },
      {
        test: /\.sass$/,
        use: [
          'vue-style-loader',
          'css-loader',
          {
            loader: 'sass-loader',
            options: {
              indentedSyntax: true
            }
          }
        ]
      }
    ]
  },
  devtool: 'source-map'
}
