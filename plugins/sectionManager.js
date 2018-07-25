const
  fs = require('fs'),
  path = require('path'),
  markdownIt = require('markdown-it'),
  config = require('../getConfig').get();

const SECTION_REGEX = /(\[section=)[a-zA-Z0-9\-]+\]/g;

module.exports = {

  listSections: [],

  process(filename, data, handlebars) {
    let fileString = data.contents.toString();
    const match = fileString.match(SECTION_REGEX);

    if (match) {
      fileString = this.injectSection(match, filename, fileString, handlebars);
    }

    return {
      has_section: match,
      sections: this.listSections.join(),
      fileContent: Buffer.from(fileString)
    };
  },

  injectSection(match, filename, fileString, handlebars) {

    match.forEach(el => {
      const
        name = el.split('=')[1].slice(0, -1),
        fullPath = path.join(__dirname, `../src/${filename.split('/').slice(0, -1).join('/')}`),
        filenames = fs.readdirSync(fullPath),
        languages = config.languages,
        md = new markdownIt();
      
      let section = '';
        
      languages.default = {fullname:'default'};
      
      filenames.forEach(file => {
        if (file.split('.')[0] === name) {
          const
            language = file.split('.')[1],
            fileContent = fs.readFileSync(fullPath + '/' + file, 'utf8'),
            template = handlebars.compile(fileContent);
          
          section += `<div id="${name}" class="section ${languages[language].fullname}">\n${md.render(template())}\n </div>`;
        }
      });

      fileString = fileString.replace(el, section);
      this.listSections.push(name);

    });

    const reMatch = fileString.match(SECTION_REGEX);

    if (reMatch) {
      fileString = this.injectSection(reMatch, filename, fileString);
    }

    return fileString;
  }

}; 
