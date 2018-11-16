const
  fs = require('fs'),
  path = require('path'),
  readYaml = require('read-yaml'),
  { Kuzzle } = require('kuzzle-sdk');


class TestCrawler {
  constructor (sdk, version, basePath) {
    this.sdk = sdk;
    this.version = version;
    this.basePath = basePath;

    this.kuzzle = new Kuzzle('websocket', { host: 'kuzzle' });

    this.snippets = [];

    this.collection = `${this.sdk}-${this.version}`;
  }

  async init () {
    try {
      await this.kuzzle.connect();
    } catch (error) {
      console.error(error);
    }
  }

  async finish () {
    return this.kuzzle.realtime.publish(
      'snippets',
      this.collection,
      { action: 'finish' }
    );
  }

  async sendSnippets () {
    this.snippets = this._crawl(this.basePath).filter(snippet => {
      const { sdk, version } = readYaml.sync(snippet);
      return sdk === this.sdk && version.toString() === this.version;
    });

    console.log(`Send ${this.snippets.length} snippets.`);

    return this.snippets.map(snippet => {
      return this.kuzzle.realtime.publish(
        'snippets',
        this.collection,
        { snippet, action: 'add' }
      );
    });
  }

  async startTesting () {
    return this.kuzzle.realtime.publish(
      'snippets',
      this.collection,
      { action: 'start' }
    );
  }

  _crawl (base) {
    let result = [];

    const files = fs.readdirSync(base);

    for (const file of files) {
      const newbase = path.join(base, file);

      if (fs.statSync(newbase).isDirectory()) {
        result = result.concat(this._crawl(newbase));
      } else if (file.indexOf('.test.yml') > -1) {
        result.push(newbase);
      }
    }

    return result;
  }

}

module.exports = TestCrawler;
