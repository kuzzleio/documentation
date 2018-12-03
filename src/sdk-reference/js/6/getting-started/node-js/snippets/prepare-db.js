const run = async () => {
  try {
    // Connect to Kuzzle backend
    await kuzzle.connect();

    // Create an index
    await kuzzle.index.create('nyc-open-data');

    // Create a collection
    await kuzzle.collection.create('nyc-open-data', 'yellow-taxi');

    console.log('nyc-open-data/yellow-taxi ready!');
  } catch (error) {
    console.error(error.message);
  } finally {
    kuzzle.disconnect();
  }
};

run();
