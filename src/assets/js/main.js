require('./app.js');
require('./algolia-search.js');
require('./drawer.js');
require('./scrollTo.js');

document.addEventListener("DOMContentLoaded", function(){
  const 
    prism = require('./prism.js'),
    Vue = require('vue/dist/vue.common'),
    Selector = require('./components/Selector.vue').default,
    elements = document.getElementsByClassName('selector-container');

  app.initialize({
    version: "1",
    url: {
      base: "/"
    }
  });

  prism.highlightAll();

  for (const element of Object.values(elements)) {
    new Vue({
      delimiters: ['${', '}'],
      el: element,
      components: {
        'Selector': Selector
      }
    });  
  }
});
