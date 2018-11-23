// load the Kuzzle SDK module
const { Kuzzle } = require('kuzzle-sdk');

// instantiate a Kuzzle client
const kuzzle = new Kuzzle('websocket', {
  host: 'kuzzle',
  autoReconnect: false
});

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error(`Network Error: ${error.message}`);
});

function sleep (ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

(async () => {
  try {
    await kuzzle.connect();
  } catch (error) {
    console.log(`Cannot connect to Kuzzle: ${error.message}`);
  }

  const consoleLog = console.log;
  const outputs = [];

  console.log = (...args) => {
    outputs.push(...args);
  };

  [snippet-code] finally {
    while (outputs.length <= 0) {
      await sleep(200);
    }

    console.log = consoleLog;
    console.log(...outputs);

    kuzzle.disconnect();
  }
})();
