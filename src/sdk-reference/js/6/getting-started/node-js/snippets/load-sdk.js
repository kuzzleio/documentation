const { Kuzzle } = require('kuzzle-sdk');

// Replace 'kuzzle' with your Kuzzle backend hostname (e.g. 'localhost')
const kuzzle = new Kuzzle('websocket', { host: 'kuzzle' });
