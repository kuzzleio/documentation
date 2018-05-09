$(document).ready(function(){
  languageSelector.init();
});


var languageSelector = {

  LANGUAGES: [
    'javascript',
    'go'
  ],

  init: function () {
    if ($('#language-selector').length == 0) {
      return
    }
    var self = this;
    var languageParam = this.getLanguageParameter();
    this.setSelectorOptions();
    $('.section').hide();
    this.showSection(languageParam);
    $('code').hide();
    $('code.' + languageParam).show();
    $('#language-selector [value="' + languageParam + '"]').attr('selected', true);
    $('#language-selector').select2({
      minimumResultsForSearch: -1,
      theme: 'material',
      templateResult: this.selectTemplate,
      templateSelection: this.selectTemplate
    });
    $(".select2-selection__arrow")
      .addClass("material-icons")
      .html("arrow_drop_down");

    $('#language-selector').on('change', function () {
      var language = $(this).val();
      self.setPreferedLanguage(language);
      window.location.replace(
        window.location.href.split('?')[0] + '?l=' + language
      );
    })
  },

  getLanguageParameter: function () {
    var GET = {};
    if (document.location.toString().indexOf('?') !== -1) {
      var query = document.location
        .toString()
        .replace(/^.*?\?/, '')
        .replace(/#.*$/, '')
        .split('&');
      for (var i = 0, l = query.length; i < l; i++) {
        var aux = decodeURIComponent(query[i]).split('=');
        GET[aux[0]] = aux[1];
      }
    }
    if (typeof GET['l'] != 'undefined' && this.LANGUAGES.indexOf(GET['l']) > -1) {
      return GET['l'];
    } else if (this.getLanguageInUrl()) {
      return this.getLanguageInUrl();
    } else if (this.getPreferedLanguage()) {
      return this.getPreferedLanguage();
    } else {
      return 'javascript'
    }
  },

  setSelectorOptions: function() {
    $('code').each(function() {
      if (typeof $(this).attr('class') != 'undefined') {
        var language = $(this).attr('class').split(' ')[1];
        var options = $('#language-selector option').map(function () { return $(this).val(); }).get();
        if (options.indexOf(language) === -1) {
          $('#language-selector').append($('<option>', {
            value: language,
            text: language
          }));
        }
      }
    });
  },

  setPreferedLanguage: function(language) {
    if(language) {
      sessionStorage.setItem('prefered_language', language)
    }
  },

  getPreferedLanguage: function () {
    return (sessionStorage.getItem('prefered_language'))
      ? sessionStorage.getItem('prefered_language') 
      : false
  },

  getLanguageInUrl: function () {
    var language = false;
    this.LANGUAGES.forEach(function(el) {
      if (document.location.toString().search('/' + el + '/') > -1){
        language = el;
      }
    })
    return language;
  },

  showSection: function (language) {
    var sections = $('#list-sections').val().split(',');
    sections.forEach(function (element) {
      if ($('#' + element + '.section.' + language).length > 0) {
        $('#' + element + '.section.' + language).show();
      } else {
        $('#' + element + '.section.default').show();
      }
    });
  },

  selectTemplate: function(state) {
    if (!state.id) {
      return state.text;
    }
    var baseUrl = "/assets/images/logos";
    var $state = $(
      '<img width="32" height="32" src="' + baseUrl + '/' + state.element.value.toLowerCase() + '.png" class="language-logo" /> <span class="language-name">' + state.text + '</span>'
    );
    return $state;
  }


}