const
  fs = require('fs'),
  path = require('path'),
  { Kuzzle } = require('kuzzle-sdk'),
  Snippet = require('./snippet'),
  Logger = require('./helpers/logger'),
  {
    getSupportedSdks,
    getVersionPath
  } = require('./helpers/utils'),
  TestResult = require('./helpers/testResult');

class TestManager {
  constructor(sdk, version) {
    const supportedSdks = getSupportedSdks();
    if (! supportedSdks.includes(sdk)) {
      throw new Error(`Unknown SDK ${sdk}. Supported SDKs: ${supportedSdks.join(', ')}`);
    }

    const Sdk = require(`./sdk/${sdk}Sdk`);
    this.sdk = new Sdk(version);

    const Runner = require(`./runners/${this.sdk.name}Runner`);
    this.languageRunner = new Runner(this.sdk);

    this.logger = new Logger(this.sdk);

    this.kuzzle = new Kuzzle('websocket', { host: 'kuzzle' });

    this.results = [];
  }

  async start () {
    try {
      await this.kuzzle.connect();

      const collection = `${this.sdk.name}-${this.sdk.version}`;

      await this.kuzzle.realtime.subscribe('snippets', collection, {}, this.runSnippet.bind(this));
      await this.kuzzle.realtime.subscribe(
        'snippets',
        collection,
        { equals: { status: 'finish' } },
        this.writeReport
      );

      console.log(`Waiting for snippet to test on 'snippets/${collection}`)
    } catch (error) {
      console.error(error);
    }
  }

  async runSnippet (notification) {
    const snippetPath = notification.result._source.snippet;
    console.log(snippetPath)

    const snippet = new Snippet(snippetPath, this.sdk);

    try {
      snippet.build();

      await this.languageRunner.run(snippet);

      this.results.push({
        code: 'SUCCESS',
        file: snippet.snippetFile
      });
    }
    catch (e) {
      if (! (e instanceof TestResult)) {
        this.results.push(new TestResult({
          code: 'ERROR',
          actual: e
        }));
      } else {
        this.results.push(e);
      }
    } finally {
      this.logger.reportResult(snippet, this.results[this.results.length - 1]);
    }
  }

  async writeReport () {
    this.logger.writeReport();

    if (this.results.filter(result => result.code !== 'SUCCESS').length > 0) {
      process.exit(1);
    }
  }

  async downloadSdk() {
    if (process.env.DEV_MODE === 'true' && this.sdk.exists()) {
      this.logger.log('DEV_MODE is true, sdk already exists, skipping download');
      return;
    }

    this.logger.log(`Install ${this.sdk.name.toUpperCase()} SDK version ${this.sdk.version} from ${getVersionPath(this.sdk)}`);

    try {
      await this.sdk.get();
      this.logger.log('Installation successfull', true);
    } catch (e) {
      this.logger.log(`Error when installing the SDK: ${e.message}`, false);

      throw e;
    }
  }
}

module.exports = TestManager;
