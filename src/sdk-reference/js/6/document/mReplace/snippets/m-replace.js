try {
  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'some-id', {});
  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'some-other-id', {});

  const response = await kuzzle.document.mReplace('nyc-open-data', 'yellow-taxi', [
    {
      _id: 'some-id',
      body: { capacity: 4 }
    },
    {
      _id: 'some-other-id',
      body: { capacity: 4 }
    }
  ]);

  console.log(response);
  console.log('Success');
} catch (error) {
  console.error(error.message);
}