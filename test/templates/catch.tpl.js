// load the Kuzzle SDK module
const { Kuzzle } = require('kuzzle-sdk');

// instantiate a Kuzzle client
const kuzzle = new Kuzzle('websocket', {
  host: 'kuzzle',
  autoReconnect: false
});

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error(`Network Error: ${error}`);
});

(async () => {
  try {
    await kuzzle.connect();
  } catch (error) {
    console.log(`Can not connect to Kuzzle: ${error.message}`);
  }
  try {
    [snippet-code]
  } catch (e) {
    console.log('Success');
  } finally {
    kuzzle.disconnect();
  }
})();
