var languageSelector = {};

languageSelector.el = document.querySelector('#language-selector');
languageSelector.excludedSDK = ['android', 'php'];
languageSelector.currentLanguage = document.location.pathname.split('/')[2];

languageSelector.init = function () {
  if (! this.el) {
    return;
  }
  
  this.setSelectorOptions();

  $(this.el).select2({
    minimumResultsForSearch: -1,
    theme: 'material',
    templateResult: this.setTemplate,
    templateSelection: this.setTemplate
  });

  $('.select2-selection__arrow')
    .addClass('material-icons')
    .html('arrow_drop_down');
    
  this.setCurrentLanguage();
  this.onChange();
};

languageSelector.setCurrentLanguage = function () {
  $(this.el)
    .val(this.currentLanguage)
    .change();
};

languageSelector.onChange = function () {
  var self = this;
  $(this.el).on('change', function() {
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

languageSelector.setSelectorOptions = function () {
  var self = this;
  Object.keys(sdkVersions)
    .filter(function(v) { return !self.excludedSDK.includes(v); })
    .forEach(function(key) {
      var language = key;
      if (key === 'js') {
        language = 'javascript';
      }
      self.el.options[self.el.options.length] = new Option(language, key);
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

languageSelector.init();