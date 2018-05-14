
const fs = require('fs');
const fsSync = require('fs-sync');
const path = require('path');
const marked = require('marked');
const color = require('colors/safe');

module.exports = {

  LANGUAGES: {
    'go': 'go',
    'js': 'javascript',
    'default': 'default'
  },

  process(filename, data) {
    var self = this;
    let fileString = data.contents.toString();
    const match = fileString.match(/(\[section=)[a-zA-Z0-9]+\]/g);
    var listSections = [];
    if (match) {
      this.report(color.green.underline(filename.replace('/index.md', '').toUpperCase()))
      match.forEach(el => {
        let name = el.split('=')[1].slice(0, -1);
        let fullPath = path.join(__dirname, '../src/' + filename.split('/').slice(0, -1).join('/') + '/sections');
        let section = '';
        let filenames = fs.readdirSync(fullPath);
        listSections.push(name);

        filenames.forEach(function (file) {
          self.report(color.green('\t  => ') + file)
          if (file.split('.')[0].split('_')[0] === name) {
            let language = file.split('.')[0].split('_')[1];
            let fileContent = fsSync.read(fullPath + '/' + file);

            section += '<div id="' + name + '" class="section ' + self.LANGUAGES[language] + '">\n' + marked(fileContent) + '\n </div>';
          }
        })
        fileString = fileString.replace(el, section)
      });
    }
    return {
      has_section: (match) ? true : false,
      sections: listSections.join(),
      fileContent: new Buffer(fileString)
    }
  },

  report(args) {
    console.log(color.green('[override-sec]'), args);
  }

}