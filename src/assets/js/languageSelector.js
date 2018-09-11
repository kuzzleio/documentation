$(document).ready(function() {
  languageSelector.init();
});

var excludedSDK = ["android", "php"];

var languageSelector = {
  init: function() {
    if ($('#language-selector').length == 0) {
      return;
    }

    var self = this;
    var languageParam = document.location.pathname.split('/')[2];

    this.setSelectorOptions();

    $('.section').hide();
    this.showSection(languageParam);

    $('pre.language-' + languageParam).show();

    $('.md-nav--secondary .md-nav__item').hide();
    $('.md-nav--secondary .md-nav__item.default').show();
    $('.md-nav--secondary .md-nav__item.' + languageParam).show();

    $('#language-selector [value="' + languageParam + '"]').attr(
      'selected',
      true
    );

    $('#language-selector').select2({
      minimumResultsForSearch: -1,
      theme: 'material',
      templateResult: this.selectTemplate,
      templateSelection: this.selectTemplate
    });

    $('.select2-selection__arrow')
      .addClass('material-icons')
      .html('arrow_drop_down');

    $('#language-selector').on('change', function() {
      var language = $(this).val();
      window.location.assign(self.getLatestVersionURL(language));
    });
  },

  getLatestVersionURL: function(language) {
    if (language == "javascript") {
      language = "js";
    }

    var baseUrl = window.location.protocol + '//' + window.location.host;

    var latestVersion = '';
    console.log(sdk_versions);
    Object.keys(sdk_versions[language]).forEach(function(key) {
      if (sdk_versions[language][key] == "master") {
        latestVersion = key;
      }
    });

    var customPathname = '/sdk-reference/' + language + '/' + latestVersion + '/essentials';
    var url = baseUrl + customPathname;

    return url;
  },

  setSelectorOptions: function() {
    Object.keys(sdk_versions).forEach(function(key) {
      if (excludedSDK.indexOf(key) == -1) {
        if (key == "js") {
          key = "javascript";
        }
        $('#language-selector').append(
          $('<option>', {
            value: key,
            text: key
          })
        );
      }
    });
  },

  showSection: function(language) {
    if ($('#list-sections').length > 0) {
      var sections = $('#list-sections')
        .val()
        .split(',');
      sections.forEach(function(element) {
        if ($('#' + element + '.section.' + language).length > 0) {
          $('#' + element + '.section.' + language).show();
        } else {
          $('#' + element + '.section.default').show();
        }
      });
    }
  },

  selectTemplate: function(state) {
    if (!state.id) {
      return state.text;
    }
    var baseUrl = '/assets/images/logos';
    var $state = $(
      '<img width="22" height="22" src="' +
        baseUrl +
        '/' +
        state.element.value.toLowerCase() +
        '.svg" class="language-logo" /> <span class="language-name">' +
        state.text +
        '</span>'
    );
    return $state;
  }
};
