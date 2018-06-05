const fsSync = require('fs-sync')
const fs = require('fs')
const path = require('path')
const config = require('../../helpers/getConfig').get()

const 
  TEMPLATE_FOLDER = path.join(__dirname, '../templates/'),
  SAVE_FOLDER = path.join(__dirname, '../bin/failed/'),
  BIN_FOLDER = path.join(__dirname, '../bin/')
  
module.exports = {
  injectSnippet: function (test, snippetPath, language) {
    let
    template = TEMPLATE_FOLDER + test.template + '.tpl.' + language,
    snippet = snippetPath + '.' + config.languages[language].ext
    
    //first check file exist
    if (!fsSync.exists(template)) {
      return false
    }
    if (!fsSync.exists(snippet)) {
      return false
    }
    
    //get file content
    let
      snippetContent = fsSync.read(snippet),
      templateContent = fsSync.read(template)
    
      //replace snippet in template
    if (templateContent.match(/(\[snippet-code])/g)) {
      let
        newContent = templateContent.replace(/(\[snippet-code])/g, snippetContent),
        binPath = BIN_FOLDER + 'bin.' + language
        
      fsSync.write(binPath, newContent)
      
      if (fsSync.exists(binPath)) {
        return binPath
      }
    }
    return false
  },
  
  indentSnippet: function (snippet, indentation) {
    return indentString(snippet, 4);
  },

  getIndentation: function (template) {
    let indentCount = 0
    lineReader.eachLine(template, function (line, last) {
      if (line.match(/(\[snippet-code])/g)) {
        return line.match(/^\s*/)[0].length
        // indentCount = line.match(/^\s*/)[0].length
      }
    })
  },
  
  isTodoAnnotation: function (snippet) {
    if (snippet.match(/(\@todo)/g)) {
      
      return true
    }
    return false
  },
  
  saveOnFail: function (binFile, testName, language) {
    testName = testName.replace(/ /g, '-').toLowerCase()
    let dest = SAVE_FOLDER + testName + '.' + language
    fsSync.copy(binFile, dest)
  },
  
}