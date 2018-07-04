kuzzle
  .index
  .getAutoRefresh('nyc-open-data')
  .then(result => {
    console.log(`autorefresh is ${result}`)
  })
  .catch(error => {
    console.error(error.message)
  });
