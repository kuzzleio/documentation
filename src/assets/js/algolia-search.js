$(document).ready(function() {
  algoliaSearch.init();
});

var algoliaSearch = {

  currentTags: window.location.pathname
    .substring(1)
    .split('/')
    .slice(0, 4)
    .map(function(tag) {
      if (tag === 'sdk-reference') { return 'sdk'; }
      if (/^[0-9]+$/.test(tag)) { return tag + '.x'; }
      return tag;
    }),

  init: function() {
    var self = this;
    var client = algoliasearch(algolia_projectId, algolia_publicKey);
    var index = client.initIndex(algolia_index);
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
        self.setResults(content.hits.sort(algoliaSearch.sortByTags), resultList);
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
      var teaser = hits[k]._highlightResult.content.value;
      content +=
        '<li class="md-search-result__item">' +
          '<a href="' + site_url + hits[k].path + '" class="md-search-result__link" title="' + hits[k].title + '">' +
            '<article class="md-search-result__article">' +
              '<h1 class="md-search-result__title">' + 
                hits[k].title + algoliaSearch.formatTags(hits[k].tags) +
              '</h1>' +
              '<p class="md-search-result__teaser">' + teaser + '</p>' +
            '</article>' +
          '</a>' +
        '</li>';
    }
    container.html(content);
  },

  formatTags: function(tags) {
    var formated = '';
    for (var k in tags) {
      formated += '<span class="tag">' + tags[k] + '</span>';
    }
    return formated;
  },
  
  sortByTags: function(a, b) {
    return algoliaSearch.getTagsScore(a.tags) < algoliaSearch.getTagsScore(b.tags);
  },
  
  getTagsScore: function(tags) {
    var score = 0;
    for(var k in tags) {
      if (algoliaSearch.currentTags.includes(tags[k])) {
        ++score;
      }
    }
    return score;
  }

};
