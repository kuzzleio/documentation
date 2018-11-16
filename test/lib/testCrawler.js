const
  fs = require('fs'),
  path = require('path'),
  readYaml = require('read-yaml'),
  { Kuzzle } = require('kuzzle-sdk');


class TestCrawler {
  constructor (basePath) {
    this.basePath = basePath;

    this.kuzzle = new Kuzzle('websocket', { host: 'kuzzle' });

    this.snippets = [];
  }

  async init () {
    try {
      this.snippets = this._crawl(this.basePath);

      await this.kuzzle.connect();

      console.log(`Found ${this.snippets.length} snippets.`);
      return true;
    } catch (error) {
      console.error(error);
    }
  }

  async sendSnippets () {
    for (const snippet of this.snippets) {
      const { name, sdk, version } = readYaml.sync(snippet);
      const collection = `${sdk}-${version}`;
      console.log(`Send ${name} to ${collection}`);

     await this.kuzzle.realtime.publish('snippets', collection, { snippet });
    }
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
