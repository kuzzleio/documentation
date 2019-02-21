try {
  // Search for documents with B as licence property
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

  console.log(`There is ${results.hits.length} document that match.`);
} catch (error) {
  console.error(error);
}
