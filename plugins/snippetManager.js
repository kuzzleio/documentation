const
  fs = require('fs'),
  path = require('path'),
  marked = require('marked'),
  markdownIt = require('markdown-it'),
  color = require('colors/safe'),
  config = require('../getConfig').get();

const SNIPPET_REGEX = /(\[snippet=)[a-zA-Z0-9\-]+\]/g;

module.exports = {

  process (filename, data) {
    let
      self = this,
      fileString = data.contents.toString(),
      match = fileString.match(SNIPPET_REGEX),
      presentLanguages = [],
      md = new markdownIt();

    if(match) {
      match.forEach(el => {
        let
          name = el.split('=')[1].slice(0, -1),
          fullPath = path.join(__dirname, '../src/' + filename.split('/').slice(0, -1).join('/') + '/' + config.code_example.snippet_folder_name),
          code = '',
          filenames = fs.readdirSync(fullPath);

        filenames.forEach(file => {

          if (file.split('.')[0] === name && file.substr(-8) !== 'test.yml' ) {
            presentLanguages.push(file.split('.')[1]);
            let fileContent = fs.readFileSync(fullPath + '/' + file, 'utf8');

            code += '``` ' + config.languages[file.split('.')[1]].fullname + '\n' + fileContent + '\n```\n';
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
        console.log(color.red(`[MISSING snippet] Language ${config.languages[k].fullname} need sample code for ${page}`))
      }
    }
  },

  report (args) {
    console.log(color.yellow('[TO-DO] =>'), args);
  }
}
