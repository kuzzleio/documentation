$(document).ready(function(){
  languageSelector.init();
});


var languageSelector = {

  init: function () {
    if ($('#language-selector').length == 0) {
      return
    }
    var self = this;
    var languageParam = this.getLanguageParameter();
    this.setSelectorOptions();
    $('code').hide();
    $('code.' + languageParam).show();
    $('#language-selector [value="' + languageParam + '"]').attr('selected', true);

    $('#language-selector').on('change', function () {
      var language = $(this).val()
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
    if (typeof GET['l'] != 'undefined') {
      return GET['l'];
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
        $('#language-selector').append($('<option>', {
          value: language,
          text: language
        }));
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
  }

}