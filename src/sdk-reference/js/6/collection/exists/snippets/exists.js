kuzzle
  .collection
  .exists('nyc-open-data', 'green-taxi')
  .then(exists => {
    if (exists) {
      console.log('Success');
    }
  })
  .catch(error => {
    console.error(error.message);
  });
