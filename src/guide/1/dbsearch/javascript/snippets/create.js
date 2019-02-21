try {
  // Delete the nyc-open-data index if exists
  if (await kuzzle.index.exists('nyc-open-data')) {
    await kuzzle.index.delete('nyc-open-data');
  }

  // Create nyc-open-data index, yellow-taxi collection and 2 documents
  // with different licence property
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

  console.log('New documents successfully created!');
} catch (error) {
  console.error(error);
}
