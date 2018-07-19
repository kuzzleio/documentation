
const fs = require('fs');
const path = require('path');
const marked = require('marked');
const config = require('../getConfig').get();

module.exports = {
  
  listSections: [],
  
  process(filename, data) {
    let fileString = data.contents.toString();
      
    if (match = fileString.match(/(\[section=)[a-zA-Z0-9]+\]/g)) {
      fileString = this.injectSection(match, filename, fileString);
    }
    
    return {
      has_section: (match) ? true : false,
      sections: [...this.listSections].join(),
      fileContent: new Buffer(fileString)
    };
  },
  
  injectSection(match, filename, fileString) {
    
    match.forEach(el => {
      const  
        name = el.split('=')[1].slice(0, -1),
        fullPath = path.join(__dirname, `../src/${filename.split('/').slice(0, -1).join('/')}`),
        filenames = fs.readdirSync(fullPath);
      
      let
        languages = config.languages,
        section = '';
        
      languages['default'] = {fullname:'default'};
      
      
      filenames.forEach(function (file) {
        if (file.split('.')[0] === name) {
          const 
            language = file.split('.')[1],
            fileContent = fs.readFileSync(fullPath + '/' + file, 'utf8');
          
          section += '<div id="' + name + '" class="section ' + languages[language].fullname + '">\n' + marked(fileContent) + '\n </div>';
        }
      });
      
      fileString = fileString.replace(el, section);
      this.listSections.push(name);
      
    });
    
    if (match = fileString.match(/(\[section=)[a-zA-Z0-9]+\]/g)) {
      fileString = this.injectSection(match, filename, fileString)
    }
    
    return fileString;
  }

} 



