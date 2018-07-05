// load the Kuzzle SDK module
const Kuzzle = require('kuzzle-sdk').Kuzzle;

// instantiate a Kuzzle client
const kuzzle = new Kuzzle('websocket', { host: 'kuzzle', autoReconnect: false });

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error('Network Error:' + error);
})

kuzzle.connect()
  .then(() => {
    return [snippet-code]
  })
  .then(() => {
    kuzzle.disconnect();
  });
