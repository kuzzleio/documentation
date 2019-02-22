// Require and instantiate kuzzle
const {
  Kuzzle,
  WebSocket
} = require('kuzzle-sdk');

const kuzzle = new Kuzzle(
  // Replace 'kuzzle' with your Kuzzle server hostname. (example: 'localhost')
  new WebSocket('kuzzle')
);

const run = async () => {
  try {
    // Wait a connection to Kuzzle to be established
    await kuzzle.connect();

    // Delete the nyc-open-data index if exists
    if (await kuzzle.index.exists('nyc-open-data')) {
      await kuzzle.index.delete('nyc-open-data');
    }

    // Create nyc-open-data index, yellow-taxi collection and 2 documents
    // with different licence property values
    await kuzzle.index.create('nyc-open-data');
    await kuzzle.collection.create('nyc-open-data', 'yellow-taxi');
    await kuzzle.document.create(
      'nyc-open-data',
      'yellow-taxi',
      { licence: 'B' }
    );
    await kuzzle.document.create(
      'nyc-open-data',
      'yellow-taxi',
      { licence: 'C' }
    );

    // Wait for document to be indexed by Elasticsearch
    await kuzzle.index.refresh('nyc-open-data');

    // Search for documents with 'B' as licence property
    const results = await kuzzle.document.search(
      'nyc-open-data',
      'yellow-taxi',
      {
        query: {
          match: {
            licence: 'B'
          }
        }
      }
    );

    console.log(`There are ${results.hits.length} matching documents.`);
  } catch (error) {
    console.error(error.message);
  } finally {
    // Disconnect from Kuzzle
    kuzzle.disconnect();
  }
};

run();
