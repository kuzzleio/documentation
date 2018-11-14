var languageSelector = {};

languageSelector.excludedSDK = ['android'];
languageSelector.currentLanguage = document.location.pathname.split('/')[2];

languageSelector.init = function (select) {
  this.setSelectorOptions(select);

  select.select2({
    minimumResultsForSearch: -1,
    theme: 'material',
    templateResult: this.setTemplate,
    templateSelection: this.setTemplate
  });

  $('.select2-selection__arrow')
    .addClass('material-icons')
    .html('arrow_drop_down');

  this.setCurrentLanguage(select);
  this.onChange(select);
};

languageSelector.setCurrentLanguage = function (select) {
  select
    .val(this.currentLanguage)
    .change();
};

languageSelector.onChange = function (select) {
  var self = this;
  select.on('change', function() {
    var language = $(this).val();
    window.location.assign(self.getLatestVersionURL(language));
  });
};

languageSelector.getLatestVersionURL = function (language) {
  var
    baseUrl = window.location.protocol + '//' + window.location.host,
    latestVersion = '',
    customPathname = '';

  if (language === 'javascript') {
    language = 'js';
  }

  latestVersion = Object.keys(sdkVersions[language]).sort().slice(-1)[0];
  customPathname = '/sdk-reference/' + language + '/' + latestVersion + '/essentials';

  return baseUrl + customPathname;
};

languageSelector.setSelectorOptions = function (select) {
  var self = this;
  Object.keys(sdkVersions)
    .filter(function(v) { return self.excludedSDK.indexOf(v) === -1; })
    .forEach(function(key) {
      var language = key;
      if (key === 'js') {
        language = 'javascript';
      }
      select.append($('<option>', {value:key, text:language}));
    });
};

languageSelector.setTemplate = function (state) {
  var
    baseUrl = '/assets/images/logos',
    $state;

  if (!state.id) {
    return state.text;
  }

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
};

$('.language-selector').each(function() {
  languageSelector.init($(this));
});