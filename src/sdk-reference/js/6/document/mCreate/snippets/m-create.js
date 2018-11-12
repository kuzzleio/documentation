try {
  const documents = [
    {
      _id: 'some-id',
      body: { 'capacity': 4 }
    },
    {
      body: { this: 'document id is auto-computed' }
    }
  ];
  const response = await kuzzle.document.mCreate(
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
  { _id: 'AWY0AoLgKWETYfLdcMat',
    _source: { _kuzzle_info: [Object], this: 'document id is auto-computed' },
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