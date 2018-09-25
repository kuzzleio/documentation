$(document).ready(function() {
  algoliaSearch.init();
});

var algoliaSearch = {
  init: function() {
    var self = this;
    var client = algoliasearch(algolia_projectId, algolia_publicKey);
    var index = client.initIndex('kuzzle-documentation');
    var searchBar = $('.md-search__input');
    var searchTrigger = $('#search');
    var resultList = $('.md-search-result__list');
    var resetButton = $('button.md-icon.md-search__icon');

    searchBar.on('input', function() {
      var query = $(this).val();
      if (query.length < 3) {
        resultList.html('');
        return;
      }
      index.search(query, function(err, content) {
        if (err) {
          return;
        }
        self.setResults(content.hits, resultList);
      });
    });

    searchTrigger.on('change', function() {
      if (!$(this).is(':checked')) {
        searchBar.val('');
        resultList.html('');
      }
    });

    resetButton.on('click', function() {
      resultList.html('');
    });
  },

  setResults: function(hits, container) {
    var content = '';
    for (var k in hits) {
      var teaser = hits[k]._snippetResult.content.value;
      content +=
        '<li class="md-search-result__item">' +
        '<a href="' +
        site_url +
        hits[k].path +
        '" class="md-search-result__link" title="' +
        hits[k].title +
        '" data-md-state="">' +
        '<article class="md-search-result__article">' +
        '<h1 class="md-search-result__title">' +
        hits[k].title +
        '</h1>' +
        '<p class="md-search-result__teaser">' +
        teaser +
        '</p>' +
        '</article>' +
        '</a>' +
        '</li>';
    }
    container.html(content);
  }
};
