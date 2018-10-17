try {
  for (let i = 0; i < 5; i++) {
    await kuzzle.document.create('nyc-open-data', 'yellow-taxi', '', {
      category: 'suv'
    });
  }
  for (let i = 5; i < 15; i++) {
    await kuzzle.document.create('nyc-open-data', 'yellow-taxi', '', {
      category: 'limousine'
    });
  }
  await kuzzle.index.refresh('nyc-open-data');

  const response = await kuzzle.document.search('nyc-open-data', 'yellow-taxi', {
    query: {
      match: {
        category: 'suv'
      }
    }
  });

  console.log(`Successfully retrieved ${response.total} documents`);
} catch (error) {
  console.error(error.message);
}