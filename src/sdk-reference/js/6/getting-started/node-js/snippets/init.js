// Loads the Kuzzle SDK modules
const {
  Kuzzle,
  Websocket
} = require('kuzzle-sdk');

// Instantiates a Kuzzle client with the Websocket protocol
// Replace 'kuzzle' with your Kuzzle server hostname (e.g. 'localhost')
const kuzzle = new Kuzzle(
  new Websocket({ host: 'kuzzle' })
);

// Adds a listener to detect connection problems
kuzzle.on('networkError', error => {
  console.error('Network Error:', error);
});

const run = async () => {
  try {
    // Connects to the Kuzzle server
    await kuzzle.connect();

    // Creates an index
    await kuzzle.index.create('nyc-open-data');

    // Creates a collection
    await kuzzle.collection.create('nyc-open-data', 'yellow-taxi');

    console.log('nyc-open-data/yellow-taxi ready!');
  } catch (error) {
    console.error(error.message);
  } finally {
    kuzzle.disconnect();
  }
};

run();
