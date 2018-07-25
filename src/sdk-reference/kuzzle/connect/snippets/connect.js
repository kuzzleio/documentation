kuzzle
  .connect()
  .then(() => {
    console.log('Successfully connected')
  })
  .catch(error => {
    console.error(error.message)
  });
