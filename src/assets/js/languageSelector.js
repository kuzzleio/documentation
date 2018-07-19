$(document).ready(function(){
  languageSelector.init();
});

const languageSelector = {

  init: function () {
    if ($('#language-selector').length == 0) {
      return;
    }

    const
      self = this,
      languageParam = this.getLanguageParameter();

    this.setSelectorOptions();

    $('.section').hide();
    this.showSection(languageParam);

    $('code').each(function(){
      const current = $(this);

      if (typeof current.attr('class') !== 'undefined' && current.attr('class') !== 'hljs') {
        current.hide();
      }
    });

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
      const language = $(this).val();
      self.setPreferedLanguage(language);
      window.location.replace(
        window.location.href.split('?')[0] + '?language=' + language
      );
    })
  },

  getLanguageParameter: function () {
    const GET = {};
    if (document.location.toString().indexOf('?') !== -1) {
      const query = document.location
        .toString()
        .replace(/^.*?\?/, '')
        .replace(/#.*$/, '')
        .split('&');
      for (const i = 0, l = query.length; i < l; i++) {
        const aux = decodeURIComponent(query[i]).split('=');
        GET[aux[0]] = aux[1];
      }
    }
    if (typeof GET['language'] != 'undefined' && languages.indexOf(GET['language']) > -1) {
      return GET['language'];
    } else if (this.getPreferedLanguage()) {
      return this.getPreferedLanguage();
    }
    return 'javascript';
  },

  setSelectorOptions: function() {
    languages.split(',').forEach(function(el) {
      $('#language-selector').append($('<option>', {
        value: el,
        text: el
      }));
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
    const language = false;
    languages.split(',').forEach(function(el) {
      if (document.location.toString().search('/' + el + '/') > -1){
        language = el;
      }
    })
    return language;
  },

  showSection: function (language) {
    if ($('#list-sections').length > 0) {
      const sections = $('#list-sections').val().split(',');
      sections.forEach(function (element) {
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
    const baseUrl = "/assets/images/logos";
    const $state = $(
      '<img width="32" height="32" src="' + baseUrl + '/' + state.element.value.toLowerCase() + '.png" class="language-logo" /> <span class="language-name">' + state.text + '</span>'
    );
    return $state;
  }


}
