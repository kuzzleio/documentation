// Create a new index named 'nyc-open-data'
kuzzle
  .index
  .create('nyc-open-data')
  .then(result => {
    console.log('index created')
  })
  .catch(error => {
    console.error(error.message)
  });
