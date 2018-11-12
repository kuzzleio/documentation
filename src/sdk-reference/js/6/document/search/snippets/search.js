const suv = { category: 'suv' };
const limousine = { category: 'suv' };

try {
  const requests = [];

  for (let i = 0; i < 5; i++) {
    requests.push(kuzzle.document.create('nyc-open-data', 'yellow-taxi', '', suv));
  }
  for (let i = 5; i < 15; i++) {
    requests.push(kuzzle.document.create('nyc-open-data', 'yellow-taxi', '', limousine));
  }
  await Promise.all(requests);

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
  console.log(`Successfully retrieved ${response.total} documents`);
} catch (error) {
  console.error(error.message);
}