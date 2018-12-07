require('select2');
require('./app.js');
require('./prism.js');
require('./algolia-search.js');
require('./drawer.js');
require('./languageSelector.js');
require('./versionSelector.js');
require('./scrollTo.js');

$(document).ready(function () {

  app.initialize({
    version: "1",
    url: {
      base: "/"
    }
  });

  prism.highlightAll();
});
