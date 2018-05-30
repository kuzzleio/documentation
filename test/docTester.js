const read = require('read-yaml')
const fsSync = require('fs-sync')
const fs = require('fs')
const path = require('path')
const nexpect = require('nexpect')
const color = require('colors/safe')
const prettier = require('prettier')
const getConfig = require('../helpers/getConfig')

var docTester = {
  
  testScript: path.join(__dirname, '../src/sdk-reference/essentials/code-example/yolo.yml'),
  config: getConfig.get(),
  TEMPLATE_FOLDER: path.join(__dirname, './templates/'),
  SAVE_FOLDER: path.join(__dirname, './bin/failed/'),
  BIN_FOLDER: path.join(__dirname, './bin/'),
  SNIPPETS_FOLDER_NAME: path.join(__dirname, './bin/'),

  process: function (language) {
    let test = read.sync(this.testScript),
      testPath = path.join(__dirname, '../src/sdk-reference/essentials/code-example/yolo')
      
    if (this.checkLanguageExist(language)) {
      this.runOneTest(test, testPath, language)
    } else {
      console.log('Language specified in args doesn\'t exist in config')
    }
  },
  
  checkLanguageExist: function (language) {
    return (this.config.languages[language] === undefined)
      ? false 
      : true
  },
  
  getAllTests: function (language) {
    
  },
  
  runOneTest: function (test, path, language) {
    if (test.hooks.before) this.runBeforeScript(test.hooks.before)
    let binFile = this.injectSnippet(
      this.TEMPLATE_FOLDER + test.template + '.tpl.' + language,
      path + '.' + this.config.languages[language].ext,
      language
    )
    if (binFile) {
      let testSuccess = true
      this.runExpect(binFile, test.expect)
      .catch((err) => {
        testSuccess = false
        this.saveOnFail(binFile, test.name, language)
        this.reportNOk(test, err)
      })
      .then(() => {
        if(testSuccess) {
          this.reportOk(test)
        }
      })
    } else {
      this.reportNOk(test, false)
    }
  },

  runExpect: function(binFile, expected) {
    return new Promise((resolve, reject) => {
      nexpect.spawn('node ' + binFile)
        .wait(expected)
        .run((err) => {
          if (err) {
            reject(err)
          } else {
            resolve()
          }
        })
    })
  },

  runBeforeScript: function () {

  },

  runAfterScript: function () {

  },

  readConfigTest: function (filename) {
    return read.sync(filename)
  },

  injectSnippet: function (template, snippet, language) {
    //first check file exist
    if (!fsSync.exists(template)) {
      return false
    }
    if (!fsSync.exists(snippet)) {
      return false
    }
    //get file content
    let snippetContent = fsSync.read(snippet),
      templateContent = fsSync.read(template)
    //replace snippet in template
    if (templateContent.match(/(\[snippet-code])/g)) {
      let newContent = templateContent.replace(/(\[snippet-code])/g, snippetContent),
        binPath = this.BIN_FOLDER + 'bin.' + language
      newContent = prettier.format(newContent)
      fsSync.write(binPath, newContent)
      if (fsSync.exists(binPath)) {
        return binPath
      }
    } 
    return false
  },

  reportOk: function (test) {
    console.log(
      color.green("✔"), color.green(test.name + ': ' + test.description)
    )
  },

  reportNOk: function (test, err) {
    console.log(color.red("✗"), color.red(test.name + ': ' + test.description + ' '))
    if(err) {
      console.log(color.red('    ' + err.code))
      console.log(color.red('    EXPECTED :') , test.expect)
      console.log(color.red('    GOT      :' ) , err.actual)
    }
  },

  saveOnFail: function (binFile, testName, language) {
    testName = testName.replace(/ /g, '-').toLowerCase()
    let dest = this.SAVE_FOLDER + testName + '.' + language
    fsSync.copy(binFile, this.SAVE_FOLDER + testName + '.' + language)
  },

}

if (process.argv.indexOf('-L') > -1) {
  const language = process.argv[process.argv.indexOf('-L') + 1]
  docTester.process(language)
} else {
  console.log('You have to define a language with -L args')
}
