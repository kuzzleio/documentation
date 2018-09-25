kuzzle.index
  .getAutoRefresh('nyc-open-data')
  .then(status => {
    console.log(`autorefresh is ${status}`);
  })
  .catch(error => {
    console.error(error.message);
  });
