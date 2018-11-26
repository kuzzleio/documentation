const webpack = require('webpack');
const middleware = require('webpack-dev-middleware');
const express = require('express');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const Puppeteer = require('puppeteer');

const compiler = webpack({
  mode: 'development',
  entry: 'snippet.js', // <-- le probleme est ici!
  devtool: 'source-map',
  output: {
    path: path.resolve(__dirname, 'dist')
  },
  devServer: {
    contentBase: './dist'
  },
  plugins: [
    new HtmlWebpackPlugin()
  ]
});
const app = express();

app.use(
  middleware(compiler, {
    // webpack-dev-middleware options
  })
);

app.listen(3000, () => console.log('Example app listening on port 3000!'));

Puppeteer.launch({
  dumpio: true
}).then(browser => {
  return browser.newPage();
}).then(page => {
  page.goto('http://localhost:3000');
});
