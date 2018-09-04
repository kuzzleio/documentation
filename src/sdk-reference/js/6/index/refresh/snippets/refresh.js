kuzzle.index
  .refresh('nyc-open-data')
  .then(response => {
    console.log(`${response._shards.failed} shards fail to refresh`);
  })
  .catch(error => {
    console.error(error.message);
  });
