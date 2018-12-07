let algoliaSearch = {

  currentTags: window.location.pathname
    .substring(1)
    .split('/')
    .slice(0, 4)
    .map(tag => {
      if (tag === 'sdk-reference') { return 'sdk'; }
      if (/^[0-9]+$/.test(tag)) { return tag + '.x'; }
      return tag;
    }),

  init: () => {
    const 
      client = algoliasearch(algolia_projectId, algolia_publicKey),
      index = client.initIndex(algolia_index),
      searchBar = $('.md-search__input'),
      searchTrigger = $('#search'),
      resultList = $('.md-search-result__list'),
      resetButton = $('button.md-icon.md-search__icon');

    searchBar.on('input', function() {
      let query = $(this).val();
      if (query.length < 3) {
        resultList.html('');
        return;
      }
      index.search(query, (err, content) => {
        if (err) {
          return;
        }
        algoliaSearch.setResults(content.hits.sort(algoliaSearch.sortByTags), resultList);
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

  setResults: (hits, container) => {
    let content = '';
    for (const hit of Object.values(hits)) {
      content +=
        `<li class="md-search-result__item">
          <a href="${site_url + hit.path}" class="md-search-result__link" title="${hit.title}">
            <article class="md-search-result__article">
              <h1 class="md-search-result__title"> 
                ${hit.title + algoliaSearch.formatTags(hit.tags)}
              </h1>
              <p class="md-search-result__teaser">${hit._highlightResult.content.value}</p>
            </article>
          </a>
        </li>`;
    }
    container.html(content);
  },

  formatTags: tags => {
    let formated = '';
    for (const tag of Object.values(tags)) {
      formated += `<span class="tag">${tag}</span>`;
    }
    return formated;
  },
  
  sortByTags: (a, b) => {
    const 
      scoreA = algoliaSearch.getTagsScore(a.tags),
      scoreB = algoliaSearch.getTagsScore(b.tags);

    return Math.sign(scoreB - scoreA);
  },

  getTagsScore: (tags) => {
    let score = 0;
    for (const tag of Object.values(tags)) {
      if (algoliaSearch.currentTags.includes(tag)) {
        ++score;
      }
    }
    return score;
  }

};

$(document).ready(function() {
  algoliaSearch.init();
});