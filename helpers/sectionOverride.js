
const fs = require('fs');
const fsSync = require('fs-sync');
const path = require('path');
const marked = require('marked');
const color = require('colors/safe');
const getConfig = require('./getConfig')

module.exports = {

  config: getConfig.get(),
  
  process(filename, data) {
    let self = this,
      languages = self.config.languages,
      fileString = data.contents.toString(),
      match = fileString.match(/(\[section=)[a-zA-Z0-9]+\]/g),
      listSections = [];
      
      languages['default'] = {fullname:'default'};
      
    if (match) {
      this.report(color.green.underline(filename.replace('/index.md', '').toUpperCase()))
      match.forEach(el => {
        let name = el.split('=')[1].slice(0, -1),
          fullPath = path.join(__dirname, '../src/' + filename.split('/').slice(0, -1).join('/') + '/' + self.config.code_example.section_folder_name),
          section = '',
          filenames = fs.readdirSync(fullPath);
          
        listSections.push(name);

        filenames.forEach(function (file) {
          self.report(color.green('\t  => ') + file)
          if (file.split('.')[0].split('_')[0] === name) {
            let language = file.split('.')[0].split('_')[1];
            let fileContent = fsSync.read(fullPath + '/' + file);

            section += '<div id="' + name + '" class="section ' + languages[language].fullname + '">\n' + marked(fileContent) + '\n </div>';
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