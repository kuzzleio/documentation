
// load the Kuzzle SDK module
const Kuzzle = require('kuzzle-sdk')

// instantiate a Kuzzle client
const kuzzle = new Kuzzle('kuzzle', { autoReconnect: false })

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
