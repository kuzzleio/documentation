const fs = require('fs');
const path = require('path');
const indentString = require('indent-string');
const sanitize = require("sanitize-filename");
const config = require('../../../getConfig').get();


const
  TEMPLATE_FOLDER = path.join(__dirname, '../../templates/'),
  SAVE_FOLDER = path.join(__dirname, '../../../reports/failed/'),
  BIN_FOLDER = path.join(__dirname, '../../bin/'),
  GENERIQUE_JAVA_CLASSNAME = 'CodeExampleGenericClass';

class FileProcess {

  injectSnippet (test, snippetPath, language) {
    let
      template = TEMPLATE_FOLDER + test.template + '.tpl.' + language,
      snippet = snippetPath + '.' + config.languages[language].ext;

    //first check file exist
    if (!fs.existsSync(template) || !fs.existsSync(snippet)) {
      return false;
    }

    //get file content
    let
      snippetContent = fs.readFileSync(snippet, 'utf8'),
      templateContent = fs.readFileSync(template, 'utf8');

    //replace snippet in template
    if (templateContent.match(/(\[snippet-code])/g)) {
      let indentationCount = this.getIndentation(templateContent);

      snippetContent = this.indentSnippet(snippetContent, indentationCount)

      let
        newContent = templateContent.replace(/(\[snippet-code])/g, snippetContent),
        fileName = this.sanitizeFileName(test.name),
        binPath = BIN_FOLDER + fileName + '.' + language;

      // JAVA hack, because filename has to be the same of the class name
      // We replace the template generique class name by the name of the test
      if (language === 'java') {
        newContent = this.overrideClassName(newContent, fileName);
      }

      fs.writeFileSync(binPath, newContent);

      if (fs.existsSync(binPath)) {
        return binPath;
      }
    }
    return false;
  }

  indentSnippet (snippet, indentation) {
    let firstline = snippet.split('\n')[0];
    snippet = snippet.replace(firstline, '');
    return firstline + indentString(snippet,indentation);
  }

  getIndentation (template) {
    let matches = template.match(/^.*snippet-code.*$/gm);
    return matches[0].match(/^\s*/)[0].length;
  }

  saveOnFail(binFile, testName, language) {
    testName = this.sanitizeFileName(testName)
    let dest = SAVE_FOLDER + testName + '.' + language;
    fs.copyFileSync(binFile, dest);
    return true;
  }

  removeBin(binFile) {
    fs.unlink(binFile, (err) => {
      if (err) throw err;
    });
  }

  sanitizeFileName(fileName) {
    return sanitize(fileName).replace(' ', '_').toLowerCase();
  }

  overrideClassName(content, name) {
    return content.replace(GENERIQUE_JAVA_CLASSNAME, name);
  }

}

module.exports = new FileProcess();
