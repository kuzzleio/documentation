const read = require('read-yaml')
const path = require('path')
const fileProcess = require('./lib/fileProcess')
const logger = require('./lib/logger')
const config = require('../helpers/getConfig').get()

class DocTester {
  
  constructor(language) {
    if (this.checkLanguageExist(language)) {
      let Tester = require(`./lib/testers/${language}Tester`);
      this.tester = new Tester(language);  
      this.language = language;
    } else {
      console.log('Language specified in args doesn\'t exist in config');
      process.exit(1);
    }
    
  }
  
  process() {
    let 
      snippetPath = path.join(__dirname, '../src/sdk-reference/essentials/code-example/yolo'),
      testYaml = path.join(__dirname, '../src/sdk-reference/essentials/code-example/yolo.yml'),
      test = read.sync(testYaml);
      
    this.tester.runOneTest(test, snippetPath);
    
  }
  
  checkLanguageExist(language) {
    return (config.languages[language] === undefined)
      ? false 
      : true
  }
  
  getAllTests() {
    
  }
  
  

  readConfigTest(filename) {
    return read.sync(filename);
  }

}

if (process.argv.indexOf('-L') > -1) {
  const 
    language = process.argv[process.argv.indexOf('-L') + 1],
    docTester = new DocTester(language);
  docTester.process(language);
} else {
  console.log('You have to define a language with -L args');
  process.exit(1);
}
