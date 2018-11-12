const suv = { category: 'suv' };
const limousine = { category: 'suv' };

try {
  for (let i = 0; i < 5; i++) {
    await Promise.all(
      kuzzle.document.create('nyc-open-data', 'yellow-taxi', '', suv)
    );
  }
  for (let i = 5; i < 15; i++) {
    await Promise.all(
      kuzzle.document.create('nyc-open-data', 'yellow-taxi', '', limousine)
    );
  }
  await kuzzle.index.refresh('nyc-open-data');

  const response = await kuzzle.document.search(
    'nyc-open-data',
    'yellow-taxi',
    {
      query: {
        match: {
          category: 'suv'
        }
      }
    }
  );

  console.log(response);
  console.log(`Successfully retrieved ${response.total} `);
} catch (error) {
  console.error(error.message);
}