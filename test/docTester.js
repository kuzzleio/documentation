const read = require('read-yaml');
const path = require('path');
const fs = require('fs');
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
      count = 0,
      allResults = [];
    
    for(let i = 0; i < tests.length; ++i) {
      let
        file = tests[i],
        test = read.sync(file),
        snippetPath = file.split('.yml')[0];
      
      this.tester.runOneTest(test, snippetPath)
        .then(()=>{
          allResults.push(true);
          count++;
          this.handleTestsFinish(count, test.length, allResults);
        })
        .catch(()=>{
          allResults.push(false);
          count++;
          this.handleTestsFinish(count, test.length, allResults);
        })
    }
  }
  
  handleTestsFinish(count, length, allResults) {
    if (count == length) {
      if (allResults.includes(false)) {
        console.log('tests failed');
        process.exit(1);
      } else {
        console.log('tests success');
        process.exit(0);
      }
    }
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

  let success = docTester.process(language);
  
} else {
  console.log('You have to define a language with -L args');
  process.exit(1);
}
