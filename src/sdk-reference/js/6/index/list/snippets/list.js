kuzzle.index
  .list()
  .then(response => {
    console.log(`Kuzzle contains ${response.length} indexes`);
    console.log(response);
  })
  .catch(error => {
    console.error(error.message);
  });
