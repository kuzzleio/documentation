kuzzle.index
  .setAutoRefresh('nyc-open-data', true)
  .then(autorefresh => {
    console.log(`autorefresh flag is set to ${autorefresh}`);
  })
  .catch(error => {
    console.error(error.message);
  });
