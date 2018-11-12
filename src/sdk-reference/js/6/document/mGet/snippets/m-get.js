const doc1 = { capacity: 4 };
const doc2 = { capacity: 7 };

try {
  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'some-id', doc1);
  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'some-other-id', doc2);

  const response = await kuzzle.document.mGet(
    'nyc-open-data',
    'yellow-taxi',
    ['some-id', 'some-other-id']
  );

  console.log(response);
  console.log('lol');
} catch (error) {
  console.error(error.message);
}