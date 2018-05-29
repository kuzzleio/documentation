const read = require('read-yaml')
const fs = require('fs-sync')
const path = require('path')
const nexpect = require('nexpect')
const color = require('colors/safe')
const prettier = require('prettier')

var docTester = {
  
  testScript: path.join(__dirname, '../src/sdk-reference/essentials/code-example/yolo.yml'),
  LANGUAGES: [
    'js'
  ],
  TEMPLATE_FOLDER: path.join(__dirname, './templates/'),
  SAVE_FOLDER: path.join(__dirname, './bin/failed/'),
  BIN_FOLDER: path.join(__dirname, './bin/'),

  process: function () {
    
    let config = read.sync(this.testScript)
    let testPath = path.join(__dirname, '../src/sdk-reference/essentials/code-example/yolo')
    this.runOneTest(config, testPath, 'js')
  },

  runOneTest: function (config, path, language) {
    if (config.hooks.before) this.runBeforeScript(config.hooks.before)
    let binFile = this.injectSnippet(
      this.TEMPLATE_FOLDER + config.template + '.tpl.' + language,
      path + '.' + language,
      'js'
    )
    if (binFile) {
      let testSuccess = true
      this.runExpect(binFile, config.expect)
      .catch((err) => {
        testSuccess = false
        this.saveOnFail(binFile, config.name, language)
        this.reportNOk(config, err)
      })
      .then(() => {
        if(testSuccess) {
          this.reportOk(config)
        }
      })
    } else {
      this.reportNOk(config, false)
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

  readConfig: function (filename) {
    return read.sync(filename)
  },

  injectSnippet: function (template, snippet, language) {
    //first check file exist
    if (!fs.exists(template)) {
      return false
    }
    if (!fs.exists(snippet)) {
      return false
    }
    //get file content
    let snippetContent = fs.read(snippet)
    let templateContent = fs.read(template)
    //replace snippet in template
    if (templateContent.match(/(\[snippet-code])/g)) {
      let newContent = templateContent.replace(/(\[snippet-code])/g, snippetContent)
      newContent = prettier.format(newContent)
      let binPath = this.BIN_FOLDER + 'bin.' + language
      fs.write(binPath, newContent)
      if (fs.exists(binPath)) {
        return binPath
      }
    } 
    return false
  },

  reportOk: function (config) {
    console.log(
      color.green("✔"), color.green(config.name + ': ' + config.description)
    )
  },

  reportNOk: function (config, err) {
    console.log(color.red("✗"), color.red(config.name + ': ' + config.description + ' '))
    if(err) {
      console.log(color.red('    ' + err.code))
      console.log(color.red('    EXPECTED :') , config.expect)
      console.log(color.red('    GOT      :' ) , err.actual)
    }
  },

  saveOnFail: function (binFile, testName, language) {
    testName = testName.replace(/ /g, '-').toLowerCase()
    let dest = this.SAVE_FOLDER + testName + '.' + language
    fs.copy(binFile, this.SAVE_FOLDER + testName + '.' + language)
  }


}

docTester.process()