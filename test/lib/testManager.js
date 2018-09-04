const
  fs = require('fs'),
  path = require('path'),
  Snippet = require('./snippet'),
  Logger = require('./helpers/logger'),
  {
    getSupportedLanguages,
    getVersionPath
  } = require('./helpers/sdk'),
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

    const supportedLanguages = getSupportedLanguages();
    if (! supportedLanguages.includes(language)) {
      throw new Error(`Unknown language ${language}. Supported languages: ${supportedLanguages.join(',')}`);
    }

    this.basePath = basePath;
    this.language = language;
    this.version = version;

    const Runner = require(`./runners/${language}Runner`);
    this.languageRunner = new Runner();

    this.logger = new Logger(this.language);

    const Sdk = require(`./sdk/${this.language}Sdk`);
    this.sdk = new Sdk(this.version);
  }

  async run() {
    const
      results = [],
      testFiles = this._getTestFiles(this.basePath);

    this.logger.log(`${testFiles.length} tests found\n`);

    for (const testFile of testFiles) {
      const snippet = new Snippet(testFile, this.language);

      try {
        snippet.build();

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
        } else {
          results.push(e);
        }
      } finally {
        this.logger.reportResult(snippet, results[results.length - 1]);
      }
    }

    this.logger.writeReport();

    if (results.filter(result => result.code !== 'SUCCESS').length > 0) {
      process.exit(1);
    }
  }

  async downloadSdk() {
    this.logger.log(`Install ${this.language.toUpperCase()} SDK version ${getVersionPath(this.language, this.version)}`);

    try {
      await this.sdk.get();
      this.logger.log('Installation successfull', true);
    } catch (e) {
      this.logger.log(`Error when installing the SDK: ${e.message}`, false);

      throw e;
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
