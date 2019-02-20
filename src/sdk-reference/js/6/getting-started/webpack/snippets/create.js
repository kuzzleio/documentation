// load the Kuzzle SDK module
import { Kuzzle, Websocket } from 'kuzzle-sdk';

// instantiate a Kuzzle client
const kuzzle = new Kuzzle(new Websocket('localhost'));

// add a listener to detect any connection problems
kuzzle.on('networkError', error => {
  console.error(`Network Error: ${error}`);
});

export default async () => {
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
