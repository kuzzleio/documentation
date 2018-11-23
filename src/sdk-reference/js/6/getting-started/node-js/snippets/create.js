// load the Kuzzle SDK module
const { Kuzzle } = require('kuzzle-sdk');

// instantiate a Kuzzle client
// replace "kuzzle" with your Kuzzle instance hostname, probaly "localhost"
const kuzzle = new Kuzzle('websocket', { host: 'kuzzle' });

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error(`Network Error: ${error}`);
});

const run = async () => {
  try {
    // Connect to Kuzzle server
    await kuzzle.connect();

    // Create your document
    const driver = {
      name: 'Sirkis',
      birthday: '1959-06-22',
      license: 'B'
    };
    await kuzzle.document.create('nyc-open-data', 'yellow-taxi', null, driver);

    console.log('New document successfully created!');
  } catch (error) {
    console.error(error.message);
  } finally {
    kuzzle.disconnect();
  }
};

run();
