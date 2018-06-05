const read = require('read-yaml')
const fsSync = require('fs-sync')
const path = require('path')
const nexpect = require('nexpect')

const fileProcess = require('./lib/fileProcess')
const logger = require('./lib/logger')
const config = require('../helpers/getConfig').get()

let docTester = {
  
  testScript: path.join(__dirname, '../src/sdk-reference/essentials/code-example/yolo.yml'),

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
    return (config.languages[language] === undefined)
      ? false 
      : true
  },
  
  getAllTests: function (language) {
    
  },
  
  runOneTest: function (test, snippetPath, language) {
    if (test.hooks.before) this.runBeforeScript(test.hooks.before)
    
    let binFile = fileProcess.injectSnippet(test, snippetPath, language)
      
    if (binFile) {
      let testSuccess = true
      this.runExpect(binFile, test.expect)
      .catch((err) => {
        testSuccess = false
        fileProcess.saveOnFail(binFile, test.name, language)
        logger.reportNOk(test, err)
      })
      .then(() => {
        if(testSuccess) {
          logger.reportOk(test)
        }
      })
    } else {
      logger.reportNOk(test, false)
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

}

if (process.argv.indexOf('-L') > -1) {
  const language = process.argv[process.argv.indexOf('-L') + 1]
  docTester.process(language)
} else {
  console.log('You have to define a language with -L args')
}
