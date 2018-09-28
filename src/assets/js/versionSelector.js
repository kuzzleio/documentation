var versionSelector = {};

versionSelector.el = document.querySelector('#version-selector');
versionSelector.currentLanguage = window.location.pathname.split('/')[2];
versionSelector.currentVersion = window.location.pathname.split('/')[3];


versionSelector.init = function () {
  if (! this.el) {
    return;
  }
  
  this.setSelectorOptions();
  
  $(this.el).select2({
    minimumResultsForSearch: -1,
    theme: 'material',
  });
  
  $('.select2-selection__arrow')
    .addClass('material-icons')
    .html('arrow_drop_down');
  
  this.setCurrentVersion();
  this.onChange();
};

versionSelector.setSelectorOptions = function () {
  var 
    self = this,
    versions = Object.keys(
      sdkVersions[this.currentLanguage]
    );
  
  versions.forEach(function(v) {
    self.el.options[self.el.options.length] = new Option(v + '.x', v);
  });
};

versionSelector.setCurrentVersion = function () {
  $(this.el)
    .val(this.currentVersion)
    .change();
};

versionSelector.onChange = function () {
  var
    self = this,
    baseUrl = window.location.protocol + '//' + window.location.host;
    
  $(this.el).on('change', function() {
    var path = '/sdk-reference/' + self.currentLanguage + '/' + $(self.el).val() + '/essentials';
    window.location.assign(baseUrl + path);
  });
};

versionSelector.init();

