const read = require('read-yaml');
const path = require('path');
const fs = require('fs');
const fileProcess = require('./lib/fileProcess');
const logger = require('./lib/logger');
const config = require('../helpers/getConfig').get();

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
      testsPath = path.join(__dirname, '../src/sdk-reference/'),
      tests = this.getAllTests(testsPath, 'yml'),
      successAll = false;
    
    tests.forEach((file) => {
      let
        test = read.sync(file),
        snippetPath = file.split('.yml')[0];
      
      this.tester.runOneTest(test, snippetPath);
    });
  }
  
  checkLanguageExist(language) {
    return (config.languages[language] === undefined)
      ? false 
      : true
  }
  
  getAllTests(base, ext, files, result) {
    let self = this; 
    files = files || fs.readdirSync(base);
    result = result || [];
    
    files.forEach((file) => {
      var newbase = path.join(base, file);
      if (fs.statSync(newbase).isDirectory()) {
        result = self.getAllTests(newbase, ext, fs.readdirSync(newbase), result);
      } else {
        if (file.substr(-1 * (ext.length + 1)) == '.' + ext) {
          result.push(newbase);
        }
      }
    });
    
    return result
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
