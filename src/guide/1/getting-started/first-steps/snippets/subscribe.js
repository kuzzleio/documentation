// load the Kuzzle SDK module
const {
  Kuzzle,
  WebSocket
} = require('kuzzle-sdk');
// instantiate a Kuzzle client
const kuzzle = new Kuzzle(new WebSocket('kuzzle'));
const message = {message: 'Hello, World!'};
// define a filter
const filter = {exists: {field: 'message'}};
// will be called each time a document match the filter
const callback = (notifications) => {
  console.log(
    'message received from kuzzle',
    notifications.result._source.message
  );
};
const run = async () => {
  try {
    await kuzzle.connect();
    // create a subscription on the collection matching given filters
    await kuzzle.realtime.subscribe(
      'playground',
      'mycollection',
      filter,
      callback
    );
    await kuzzle.document.create(
      'playground',
      'mycollection',
      message
    );
  } catch (error) {
    console.error(error.message);
  } finally {
    kuzzle.disconnect();
  }
};
run();