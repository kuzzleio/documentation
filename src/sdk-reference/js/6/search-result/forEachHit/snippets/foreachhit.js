try {
  const results = await kuzzle.document.search(
    'nyc-open-data',
    'yellow-taxi',
    {},
    { from: 0, size: 1 });

  const documents = [];

  results.forEachHit(hit => documents.push(hit));

  console.log(`Successfully retrieved ${documents.length} documents`);
} catch (error) {
  console.error(error.message);
}
