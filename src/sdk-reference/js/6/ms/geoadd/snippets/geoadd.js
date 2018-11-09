const
  kuzzleHQ = {
    lon: 3.9109057,
    lat: 43.6073913,
    name: 'HQ'
  },
  otherHQ = {
    lon: 3.897105,
    lat: 43.6002203,
    name: 'other HQ'
  };

try {
  await kuzzle.ms.geoadd('geofoo', [kuzzleHQ, otherHQ]);
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
