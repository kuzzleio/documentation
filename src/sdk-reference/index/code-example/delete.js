// Delete the index named 'nyc-open-data'
kuzzle
  .index
  .delete('nyc-open-data')
  .then(result => {
    console.log('index deleted')
  })
  .catch(error => {
    console.error(error.message)
  });
