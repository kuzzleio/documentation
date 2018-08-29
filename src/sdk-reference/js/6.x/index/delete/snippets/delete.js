kuzzle.index
  .delete('nyc-open-data')
  .then(response => {
    console.log('index deleted');
  })
  .catch(error => {
    console.error(error.message);
  });
