try {
  const response = kuzzle.index.create('nyc-open-data');
  console.log(response);
  /*
    { acknowledged: 1,
      shards_acknowledged: 1 }
  */

  console.log('Index created');
} catch (error) {
  console.error(error.message);
}
