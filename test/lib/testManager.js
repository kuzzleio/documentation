const
  fs = require('fs'),
  path = require('path'),
  Snippet = require('./snippet'),
  TestResult = require('./helpers/testResult');

const supportedLanguages = ['js', 'cpp', 'go', 'java'];

class TestManager {
  constructor(basePath) {
    if (basePath.indexOf('sdk-reference') === -1) {
      throw new Error('Unable to find sdk-reference directory in basePath');
    }

    const [language, version, rest] = (basePath.split('sdk-reference/')[1] || '').split('/');

    if (! language) {
      throw new Error('Unable to find language in basePath');
    }

    if (! version) {
      throw new Error('Unable to find version in basePath');
    }

    if (!supportedLanguages.includes(language)) {
      throw new Error(`Unsupported language '${language}'. Only ${supportedLanguages.join(', ')} are supported.`);
    }

    this.basePath = basePath;
    this.language = language;
    this.version = version;

    const Runner = require(`./runners/${language}Runner`);
    this.languageRunner = new Runner();
  }

  async run() {
    const
      results = [],
      testFiles = this._getTestFiles(this.basePath);

    for (const testFile of testFiles) {

      try {
        const snippet = new Snippet(testFile, this.language);

        await this.languageRunner.run(snippet);

        results.push({
          code: 'SUCCESS',
          file: snippet.snippetFile
        });
      }
      catch (e) {
        if (! (e instanceof TestResult)) {
          results.push(new TestResult({
            code: 'ERROR',
            actual: e
          }));
        }

        results.push(e);
      }
    }
    console.log(results)
    if (results.length > 0) {
//      process.exit(1);
    }
  }

  _getTestFiles(base) {
    let result = [];

    const files = fs.readdirSync(base);

    for (const file of files) {
      const newbase = path.join(base, file);

      if (fs.statSync(newbase).isDirectory()) {
        result = result.concat(this._getTestFiles(newbase))
      } else if (file.indexOf('.test.yml') > -1) {
        result.push(newbase);
      }
    }

    return result;
  }
};

module.exports = TestManager;
