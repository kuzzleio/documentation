kuzzle
  .index
  .exists('nyc-open-data')
  .then(exists => {
    if (exists === true) {
      console.log('index exists')
    } else {
      console.log('index does not exist')
    }
  })
  .catch(error => {
    console.error(error.message)
  });
