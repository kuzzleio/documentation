// load the Kuzzle SDK module
const { Kuzzle } = require('kuzzle-sdk');

// instantiate a Kuzzle client
const kuzzle = new Kuzzle('websocket', { host: 'localhost' });

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error(`Network Error: ${error}`);
});

const run = async () => {
  try {
    // Connect to Kuzzle server
    await kuzzle.connect();

    // Define a filter
    const filter = {
      equals: { license: 'B' }
    };

    // Define a callback
    const callback = (notification) => {

      if (notification.type === 'document' && notification.action === 'create') {
        const driver = notification.result._source;
        const driverId = notification.result._id;

        console.log(`New driver ${driver.name} with id ${driverId} has B license.`);
      }
    };

    // Subscribes to document notifications with our filter
    await kuzzle.realtime.subscribe('nyc-open-data', 'yellow-taxi', filter, callback);

    console.log('Successfully subscribe to document notifications!');
  } catch (error) {
    console.error(error.message);
  }
};

run();
