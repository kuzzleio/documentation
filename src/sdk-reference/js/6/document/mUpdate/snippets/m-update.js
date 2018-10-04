try {
  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'some-id', {capacity: 4});
  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'some-other-id', {capacity: 7});

  const response = await kuzzle.document.mReplace('nyc-open-data', 'yellow-taxi', [
    {
      _id: 'some-id',
      body: { category: 'sedan' }
    },
    {
      _id: 'some-other-id',
      body: { category: 'limousine' }
    }
  ]);

  console.log(response);
  /*
  { hits:
   [ { _id: 'some-id',
       _source: { _kuzzle_info: [Object], category: 'sedan' },
       _index: 'nyc-open-data',
       _type: 'yellow-taxi',
       _version: 2,
       result: 'updated',
       _shards: { total: 2, successful: 1, failed: 0 },
       created: false,
       status: 200,
       _meta:
        { active: true,
          author: '-1',
          updater: null,
          updatedAt: null,
          deletedAt: null,
          createdAt: 1538640233770 } },
     { _id: 'some-other-id',
       _source: { _kuzzle_info: [Object], category: 'limousine' },
       _index: 'nyc-open-data',
       _type: 'yellow-taxi',
       _version: 2,
       result: 'updated',
       _shards: { total: 2, successful: 1, failed: 0 },
       created: false,
       status: 200,
       _meta:
        { active: true,
          author: '-1',
          updater: null,
          updatedAt: null,
          deletedAt: null,
          createdAt: 1538640233770 } } ],
  total: 2 }
  */
  console.log('Success');
} catch (error) {
  console.error(error.message);
}