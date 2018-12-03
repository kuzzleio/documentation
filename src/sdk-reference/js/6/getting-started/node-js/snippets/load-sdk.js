const { Kuzzle } = require('kuzzle-sdk');

// replace "kuzzle" with your Kuzzle instance hostname, probaly "localhost"
const kuzzle = new Kuzzle('websocket', { host: 'kuzzle' });
