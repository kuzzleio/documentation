try {
  const body = [ 
    {
      _id: 'some-id',
      body: { 'capacity': 4 }
    },
    {
      _id: 'some-other-id',
      body: { 'capacity': 4 }
    }
  ];
  const response = await kuzzle.document.mCreateOrReplace('nyc-open-data', 'yellow-taxi', body);

  console.log(response);
  /*
  [ { _id: 'some-id',
    _source: { _kuzzle_info: [Object], capacity: 4 },
    _index: 'nyc-open-data',
    _type: 'yellow-taxi',
    _version: 1,
    result: 'created',
    _shards: { total: 2, successful: 1, failed: 0 },
    created: true,
    status: 201,
    _meta: 
     { active: true,
       author: '-1',
       updater: null,
       updatedAt: null,
       deletedAt: null,
       createdAt: 1538552685790 } },
  { _id: 'some-other-id',
    _source: { _kuzzle_info: [Object], capacity: 4 },
    _index: 'nyc-open-data',
    _type: 'yellow-taxi',
    _version: 1,
    result: 'created',
    _shards: { total: 2, successful: 1, failed: 0 },
    created: true,
    status: 201,
    _meta: 
     { active: true,
       author: '-1',
       updater: null,
       updatedAt: null,
       deletedAt: null,
       createdAt: 1538552685790 } } ]
  */
  console.log('Success');
} catch (error) {
  console.error(error.message);
}