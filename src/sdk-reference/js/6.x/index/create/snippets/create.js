kuzzle
  .index
  .create('nyc-open-data')
  .then(response => {
    console.log('index created')
  })
  .catch(error => {
    console.error(error.message)
  });
