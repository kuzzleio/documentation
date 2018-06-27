
const fs = require('fs');
const path = require('path');
const marked = require('marked');
const config = require('../getConfig').get();

module.exports = {

  process(filename, data) {
    let 
      languages = config.languages,
      fileString = data.contents.toString(),
      match = fileString.match(/(\[section=)[a-zA-Z0-9]+\]/g),
      listSections = [];
      
      languages['default'] = {fullname:'default'};
      
    if (match) {
      match.forEach(el => {
        let 
          name = el.split('=')[1].slice(0, -1),
          fullPath = path.join(__dirname, '../src/' + filename.split('/').slice(0, -1).join('/') + '/' + config.code_example.section_folder_name),
          section = '',
          filenames = fs.readdirSync(fullPath);
          
        listSections.push(name);

        filenames.forEach(function (file) {
          if (file.split('.')[0].split('_')[0] === name) {
            let language = file.split('.')[0].split('_')[1];
            let fileContent = fs.readFileSync(fullPath + '/' + file, 'utf8');

            section += '<div id="' + name + '" class="section ' + languages[language].fullname + '">\n' + marked(fileContent) + '\n </div>';
          }
        })
        
        fileString = fileString.replace(el, section);
      });
    }
    return {
      has_section: (match) ? true : false,
      sections: listSections.join(),
      fileContent: new Buffer(fileString)
    };
  }

} 