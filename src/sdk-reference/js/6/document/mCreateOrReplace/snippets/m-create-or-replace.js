try {
  const documents = [
    {
      _id: 'some-id',
      body: { 'capacity': 4 }
    },
    {
      _id: 'some-other-id',
      body: { 'capacity': 4 }
    }
  ];

  const response = await kuzzle.document.mCreateOrReplace(
    'nyc-open-data',
    'yellow-taxi',
    documents
  );

  console.log(JSON.stringify(response));
  /*
  [ { _id: 'some-id',
    _source: { _kuzzle_info: [Object], capacity: 4 },
    _index: 'nyc-open-data',
    _type: 'yellow-taxi',
    _version: 1,
    result: 'created',
    _shards: { total: 2, successful: 1, failed: 0 },
    created: true,
    status: 201 },
  { _id: 'some-other-id',
    _source: { _kuzzle_info: [Object], capacity: 4 },
    _index: 'nyc-open-data',
    _type: 'yellow-taxi',
    _version: 1,
    result: 'created',
    _shards: { total: 2, successful: 1, failed: 0 },
    created: true,
    status: 201 } ]
  */
  console.log('lol');
} catch (error) {
  console.error(error.message);
}