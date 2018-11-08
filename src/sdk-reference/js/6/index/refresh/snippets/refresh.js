kuzzle.index
  .refresh('nyc-open-data')
  .then(response => {
    console.log(`${response.failed} shards fail to refresh`);
  })
  .catch(error => {
    console.error(error.message);
  });
