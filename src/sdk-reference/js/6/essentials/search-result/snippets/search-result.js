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
  }, {
    from: 1,
    size: 2
  });

  const nextResponse = await response.next();

  console.log(`Successfully retrieved ${nextResponse.fetched} documents`);
  console.log(nextResponse);
} catch (error) {
  console.error(error.message);
}