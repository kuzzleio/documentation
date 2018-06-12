const fsSync = require('fs-sync');
const fs = require('fs');
const path = require('path');
const indentString = require('indent-string');
const sanitize = require("sanitize-filename");
const config = require('../../helpers/getConfig').get();


const 
  TEMPLATE_FOLDER = path.join(__dirname, '../templates/'),
  SAVE_FOLDER = path.join(__dirname, '../bin/failed/'),
  BIN_FOLDER = path.join(__dirname, '../bin/')
  
class FileProcess {
  
  injectSnippet (test, snippetPath, language) {
    let
      template = TEMPLATE_FOLDER + test.template + '.tpl.' + language,
      snippet = snippetPath + '.' + config.languages[language].ext;
    
    //first check file exist
    if (!fsSync.exists(template)) {
      return false;
    }
    if (!fsSync.exists(snippet)) {
      return false;
    }
    
    //get file content
    let
      snippetContent = fsSync.read(snippet),
      templateContent = fsSync.read(template);
    
    //replace snippet in template
    if (templateContent.match(/(\[snippet-code])/g)) {
      let indentationCount = this.getIndentation(templateContent);
      
      snippetContent = this.indentSnippet(snippetContent, indentationCount)
      
      let
        newContent = templateContent.replace(/(\[snippet-code])/g, snippetContent),
        binPath = BIN_FOLDER + this.sanitizeFileName(test.name) + '.' + language;
        
      fsSync.write(binPath, newContent);
      
      if (fsSync.exists(binPath)) {
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
    fsSync.copy(binFile, dest);
  }
  
  removeBin(binFile) {
    if(binFile) {
      try {
        fs.unlinkSync(binFile)
      } catch (error) {
        
      }
    }
  }
  
  sanitizeFileName(fileName) {
    return sanitize(fileName).replace(' ', '_').toLowerCase();
  }
  
}

module.exports = new FileProcess();