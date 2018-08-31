const fs = require('fs'),
  path = require('path'),
  indentString = require('indent-string'),
  sanitize = require('sanitize-filename'),
  config = require('../../../getConfig').get();

const
  TEMPLATE_FOLDER = path.join(__dirname, '../../templates/'),
  SAVE_FOLDER = path.join(__dirname, '../../../reports/failed/'),
  BIN_FOLDER = path.join(__dirname, '../../bin/'),
  GENERIQUE_JAVA_CLASSNAME = 'CodeExampleGenericClass';

class FileProcess {

  indentSnippet(snippet, indentation) {
    const firstline = snippet.split('\n')[0];
    snippet = snippet.replace(firstline, '');

    return firstline + indentString(snippet, indentation);
  }


  saveOnFail(binFile, testName, language) {
    testName = this.sanitizeFileName(testName);
    const dest = `${SAVE_FAIL_DIR}${testName}.${language}`;
    fs.copyFileSync(binFile, dest);

    return true;
  }

  remove(filePath) {
    fs.unlinkSync(filePath);
  }

  sanitizeFileName(fileName) {
    return sanitize(fileName)
      .replace(' ', '_')
      .toLowerCase();
  }

  overrideClassName(content, name) {
    return content.replace(GENERIQUE_JAVA_CLASSNAME, name);
  }
}

module.exports = new FileProcess();
