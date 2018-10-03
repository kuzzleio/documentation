try {
  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'some-id', {capacity: 4});
  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'some-other-id', {capacity: 4});

  const response = await kuzzle.document.mGet('nyc-open-data', 'yellow-taxi', ['some-id', 'some-other-id']);
  console.log(response);
  console.log('Success');
} catch (error) {
  console.error(error.message);
}