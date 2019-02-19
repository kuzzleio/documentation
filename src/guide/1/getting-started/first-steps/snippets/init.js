// load the Kuzzle SDK module
const {
  Kuzzle,
  WebSocket
} = require('kuzzle-sdk');
/* instantiate a Kuzzle client,
this will automatically connect to the Kuzzle server */
const kuzzle = new Kuzzle(new WebSocket('kuzzle'));
const run = async () => {
  try {
    await kuzzle.connect();
    // create a 'playground' index
    await kuzzle.index.create('playground');
    // create a collection named 'mycollection' in playground index
    await kuzzle.collection.create(
      'playground',
      'mycollection'
    );
    console.log('playground/mycollection ready');
  } catch (error) {
    console.error(error.message);
  } finally {
    kuzzle.disconnect();
  }
};
run();