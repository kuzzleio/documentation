kuzzle
  .index
  .create('nyc-open-data')
  .then(result => {
    console.log('index created')
  })
  .catch(error => {
    console.error(error.message)
  });
