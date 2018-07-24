
const fs = require('fs');
const path = require('path');
const marked = require('marked');
const config = require('../getConfig').get();

module.exports = {
  
  listSections: [],
  
  process(filename, data) {
    let fileString = data.contents.toString();
    const match = fileString.match(/(\[section=)[a-zA-Z0-9]+\]/g);
    
    if (match) {
      fileString = this.injectSection(match, filename, fileString);
    }
    
    return {
      has_section: match,
      sections: this.listSections.join(),
      fileContent: Buffer.from(fileString)
    };
  },
  
  injectSection(match, filename, fileString) {
    
    match.forEach(el => {
      const  
        name = el.split('=')[1].slice(0, -1),
        fullPath = path.join(__dirname, `../src/${filename.split('/').slice(0, -1).join('/')}`),
        filenames = fs.readdirSync(fullPath),
        languages = config.languages;
      
      let section = '';
        
      languages['default'] = {fullname:'default'};
      
      filenames.forEach(file => {
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
    
    const reMatch = fileString.match(/(\[section=)[a-zA-Z0-9]+\]/g); 
    
    if (reMatch) {
      fileString = this.injectSection(reMatch, filename, fileString)
    }
    
    return fileString;
  }

} 



