kuzzle.index
  .list()
  .then(response => {
    console.log(`Kuzzle contains ${response.indexes.length} indexes`);
    console.log(response.indexes);
  })
  .catch(error => {
    console.error(error.message);
  });
