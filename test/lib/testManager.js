const
  read = require('read-yaml'),
  path = require('path'),
  fs = require('fs'),
  config = require('../../getConfig').get(),
  Bluebird = require('bluebird');

module.exports = class TestManager {

  constructor(language) {
    if (! this.checkLanguageExist(language)) {
      // eslint-disable-next-line no-console
      console.log('Language specified in args doesn\'t exist in config');
      process.exit(1);
    }

    const Tester = require(`./testers/${language}Tester`);
    this.tester = new Tester();
    this.language = language;
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
      const
        test = read.sync(file),
        snippetPath = file.split('.test.yml')[0];

      return this.tester.runOneTest(test, snippetPath)
        .then(() => {
          allResults.push(true);
          count++;
          this.handleTestsFinish(count, tests.length, allResults);
        })
        .catch(err => {
          if (typeof err !== 'undefined') {
            // eslint-disable-next-line no-console
            console.log(err);
          }

          allResults.push(false);
          count++;
          this.handleTestsFinish(count, tests.length, allResults);
        });
    });
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
    const suffix = '.test';
    files = files || fs.readdirSync(base);
    result = result || [];

    files.forEach((file) => {
      var newbase = path.join(base, file);
      if (fs.statSync(newbase).isDirectory()) {
        result = this.getAllTests(newbase, ext, fs.readdirSync(newbase), result);
      } else if (file.substr(-1 * (ext.length + 6)) === `${suffix}.${ext}`) {
        result.push(newbase);
      }
    });

    return result;
  }

  readConfigTest(filename) {
    return read.sync(filename);
  }

};
