$(document).ready(function() {
  languageSelector.init();
});

var excludedSDK = ["android", "php"];

var languageSelector = {
  init: function() {
    if ($('#language-selector').length === 0) {
      return;
    }

    var
      self = this,
      languageParam = document.location.pathname.split('/')[2];

    this.setSelectorOptions();

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
    if (language === "javascript") {
      language = "js";
    }

    var
      baseUrl = window.location.protocol + '//' + window.location.host,
      latestVersion = '';

    Object.keys(sdkVersions[language]).forEach(function(key) {
      if (sdkVersions[language][key] === "master") {
        latestVersion = key;
      }
    });

    var
      customPathname = "/sdk-reference/" + language + "/" + latestVersion + "/essentials",
      url = baseUrl + customPathname;

    return url;
  },

  setSelectorOptions: function() {
    Object.keys(sdkVersions)
      .filter(function(v) { return !excludedSDK.includes(v); })
      .forEach(function(key) {
        if (key === "js") {
          key = "javascript";
        }
        $('#language-selector').append(
          $('<option>', {
            value: key,
            text: key
          })
        );
      });
  },

  selectTemplate: function(state) {
    if (!state.id) {
      return state.text;
    }
    var
      baseUrl = '/assets/images/logos'
      $state = $(
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
