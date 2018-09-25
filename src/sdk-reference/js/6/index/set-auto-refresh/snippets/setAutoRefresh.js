kuzzle.index
  .setAutoRefresh('nyc-open-data', true)
  .then(response => {
    console.log(`autorefresh flag is set to ${response.response}`);
  })
  .catch(error => {
    console.error(error.message);
  });
