kuzzle
  .index
  .list()
  .then(response => {
    console.log(`Kuzzle contains ${response.indexes.length} indexes`)
  })
  .catch(error => {
    console.error(error.message)
  });
