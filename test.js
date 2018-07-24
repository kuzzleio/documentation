// load the Kuzzle SDK module
const { Kuzzle } = require('kuzzle-sdk');

// instantiate a Kuzzle client
const kuzzle = new Kuzzle('websocket', {
  host: 'kuzzle',
  autoReconnect: false
});

kuzzle
  .addListener('connected', () => console.log('You are connected'))
  .addListener('connected', () => console.log('I already told you'));

console.log(kuzzle.listeners('connected').length)
