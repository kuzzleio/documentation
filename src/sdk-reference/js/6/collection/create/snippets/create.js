kuzzle
  .collection
  .create('nyc-open-data', 'yellow-taxi', {
    properties: {
      license: { type: 'keyword' },
      driver: {
        properties: {
          name: { type: 'keyword' },
          curriculum: { type: 'text' }
        }
      }
    }
  })
  .then(() => {
    console.log('Success');
  })
  .catch(error => {
    console.error(error.message);
  });
