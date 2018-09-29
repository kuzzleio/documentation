var versionSelector = {};

versionSelector.currentLanguage = window.location.pathname.split('/')[2];
versionSelector.currentVersion = window.location.pathname.split('/')[3];


versionSelector.init = function (select) {
  this.setSelectorOptions(select);
  
  select.select2({
    minimumResultsForSearch: -1,
    theme: 'material',
  });
  
  $('.select2-selection__arrow')
    .addClass('material-icons')
    .html('arrow_drop_down');
  
  this.setCurrentVersion(select);
  this.onChange(select);
};

versionSelector.setSelectorOptions = function (select) {
  var versions = Object.keys(
    sdkVersions[this.currentLanguage]
  );
  
  versions.forEach(function(version) {
    select.append($('<option>', {value: version, text: version + '.x'}));
  });
};

versionSelector.setCurrentVersion = function (select) {
  select
    .val(this.currentVersion)
    .change();
};

versionSelector.onChange = function (select) {
  var
    self = this,
    baseUrl = window.location.protocol + '//' + window.location.host;
    
  select.on('change', function() {
    var path = '/sdk-reference/' + self.currentLanguage + '/' + select.val() + '/essentials';
    window.location.assign(baseUrl + path);
  });
};

$('.version-selector').each(function() {
  versionSelector.init($(this));
});

