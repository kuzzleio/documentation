const read = require('read-yaml');
const fs = require('fs-sync');
const path = require('path');

var docTester = {
  
  testScript: path.join(__dirname, '../src/sdk-reference/essentials/code-example/test.yml'),
  LANGUAGES: [
    'js'
  ],
  TEMPLATE_FOLDER: path.join(__dirname, './templates/'),
  BIN_FOLDER: path.join(__dirname, './bin/'),

  process: function () {
    
    let config = read.sync(this.testScript);
    let testPath = path.join(__dirname, '../src/sdk-reference/essentials/code-example/test');
    this.runOneTest(config, testPath, 'js');
  },

  runOneTest: function (config, path, language) {
    if (config.before) this.runBeforeScript(config.before);
    let binFile = this.injectSnippet(
      this.TEMPLATE_FOLDER + config.template + '.tpl.' + language,
      path + '.' + language,
      'js'
    );
    if (binFile) {
      console.log(binFile)
    }

  },

  runBeforeScript: function () {

  },

  runAfterScript: function () {

  },

  readConfig: function (filename) {
    return read.sync(filename);
  },

  injectSnippet: function (template, snippet, language) {
    //first check file exist
    if (!fs.exists(template)) {
      return false;
    }
    if (!fs.exists(snippet)) {
      return false;
    }

    //get file content
    let snippetContent = fs.read(snippet);
    let templateContent = fs.read(template);
    //replace snippet in template
    if (templateContent.match(/(\[snippet-code])/g)) {
      let newContent = templateContent.replace(/(\[snippet-code])/g, snippetContent);
      let binPath = this.BIN_FOLDER + 'bin.' + language;
      fs.write(binPath, newContent);
      if (fs.exists(binPath)) {
        return binPath;
      }
    } 
    return false;
  }

}

docTester.process();