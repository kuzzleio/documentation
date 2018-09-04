// load the Kuzzle SDK module
const
  {
    Kuzzle,
    KuzzleAbstractNetwork
  } = require('kuzzle-sdk');

// create a custom protocol class
class MyCustomProtocol extends KuzzleAbstractNetwork {
  connect () {
    this.state = 'ready';
    return Promise.resolve();
  }

  send (request) {
    return Promise.resolve();
  }
}

[snippet-code]
console.log('Everything is ok');
