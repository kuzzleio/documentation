const { Kuzzle } = require('kuzzle-sdk');

// replace 'kuzzle' with your Kuzzle backend hostname, probaly 'localhost'
const kuzzle = new Kuzzle('websocket', { host: 'kuzzle' });
