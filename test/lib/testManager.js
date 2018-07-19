const read = require('read-yaml');
const path = require('path');
const fs = require('fs');
const file = require('fs');
const config = require('../../getConfig').get();
const Bluebird = require('bluebird');

module.exports = class TestManager {

  constructor(language) {
    if (this.checkLanguageExist(language)) {
      const Tester = require(`./testers/${language}Tester`);
      this.tester = new Tester();
      this.language = language;
    } else {
      console.log('Language specified in args doesn\'t exist in config');
      process.exit(1);
    }
  }

  process(onlyOnePath) {
    let
      testsPath = path.join(__dirname, '../../src/sdk-reference/'),
      tests,
      count = 0,
      allResults = [];

    if (onlyOnePath) {
      tests = this.getAllTests(testsPath, 'yml', [onlyOnePath]);
    } else {
      tests = this.getAllTests(testsPath, 'yml');
    }

    Bluebird.mapSeries(tests, file => {
      let
        test = read.sync(file),
        snippetPath = file.split('.yml')[0];

      return this.tester.runOneTest(test, snippetPath)
        .then(() => {
          allResults.push(true);
          count++;
          this.handleTestsFinish(count, tests.length, allResults);
        })
        .catch((err) => {
          if (typeof err !== 'undefined') console.log(err);
          allResults.push(false);
          count++;
          this.handleTestsFinish(count, tests.length, allResults);
        })
    })
  }

  handleTestsFinish(count, length, allResults) {
    if (count === length) {
      if (allResults.includes(false)) {
        process.exit(1);
      } else {
        process.exit(0);
      }
    }
  }

  checkLanguageExist(language) {
    return ! (config.languages[language] === undefined);
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
