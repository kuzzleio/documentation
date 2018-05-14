
const fs = require('fs');
const fsSync = require('fs-sync');
const path = require('path');
const color = require('colors/safe');

module.exports = {

  LANGUAGES: {
    'go': 'go',
    'js': 'javascript'
  },

  process (filename, data) {
    var self = this;
    let fileString = data.contents.toString();
    const match = fileString.match(/(\[code-example=)[a-zA-Z0-9]+\]/g);
    if(match) {
      this.report(color.yellow.underline(filename.replace('/index.md', '').toUpperCase()))
      match.forEach(el => {
        let name = el.split('=')[1].slice(0, -1);
        let fullPath = path.join(__dirname, '../src/' + filename.split('/').slice(0, -1).join('/') + '/code-example');
        let code = '';
        let filenames = fs.readdirSync(fullPath)
        filenames.forEach(function (file) {
          self.report(color.yellow('\t  => ')  + file)
          if (file.split('.')[0] === name) {
            let fileContent = fsSync.read(fullPath + '/' + file)
            code += '``` ' + self.LANGUAGES[file.split('.')[1]] + '\n ' + fileContent + '\n```\n'
          }
        })
        fileString = fileString.replace(el, code)
      });
    }
    return {
      has_code_example : (match) ? true : false,
      fileContent: new Buffer(fileString)
    }
  },

  report(args) {
    console.log(color.yellow('[code-example]'), args);
  }
}