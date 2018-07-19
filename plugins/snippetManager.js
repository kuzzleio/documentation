
const fs = require('fs');
const path = require('path');
const marked = require('marked');
const markdownIt = require('markdown-it');
const color = require('colors/safe');
const config = require('../getConfig').get();

module.exports = {

  process (filename, data) {
    let 
      self = this,
      fileString = data.contents.toString(),
      match = fileString.match(/(\[code-example=)[a-zA-Z0-9]+\]/g),
      presentLanguages = [],
      md = new markdownIt();
      
    if(match) {
      match.forEach(el => {
        let
          name = el.split('=')[1].slice(0, -1),
          fullPath = path.join(__dirname, '../src/' + filename.split('/').slice(0, -1).join('/') + '/' + config.code_example.snippet_folder_name),
          code = '',
          filenames = fs.readdirSync(fullPath);
        
        filenames.forEach(function (file) {
          
          if (file.split('.')[0] === name && file.substr(-8) !== 'test.yml' ) {
            presentLanguages.push(file.split('.')[1]);
            let fileContent = fs.readFileSync(fullPath + '/' + file, 'utf8');
            
            if (fileContent.match(/(\@todo)/g)) {
              code += '``` ' + config.languages[file.split('.')[1]].fullname + '\n Not implemented yet \n```\n';
              let page = fullPath.split('sdk-reference/')[1] + '/' + file;
              self.report(page);
            } else if (fileContent.match(/(\@wontdo)/g)) {
              code += '``` ' + config.languages[file.split('.')[1]].fullname + '\n Not availlable \n```\n';
            } else {
              code += '``` ' + config.languages[file.split('.')[1]].fullname + '\n' + fileContent + '\n```\n';
            }
          }
          
        });
        const markdown = md.render(code);
        this.checkMissingLanguages(presentLanguages, fullPath)
        fileString = fileString.replace(el, markdown);
      });
    }
    return {
      has_code_example : (match) ? true : false,
      fileContent: new Buffer(fileString)
    };
  },
  
  checkMissingLanguages (presentLanguages, fullPath) {
    let exts = []
    for (let k in config.languages) {
      if (!presentLanguages.includes(config.languages[k].ext)) {
        let page = fullPath.split('sdk-reference')[1].split(config.code_example.snippet_folder_name)[0]
        console.log(color.red(`[MISSING CODE-EXAMPLE] Language ${config.languages[k].fullname} need sample code for ${page}`))
      }
    }
  },
  
  report (args) {
    console.log(color.yellow('[TO-DO] =>'), args);
  }
}