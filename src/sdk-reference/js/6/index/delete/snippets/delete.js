try {
  const response = kuzzle.index.delete('nyc-open-data');
  console.log(response);
  /*
    { acknowledged: 1 }
  */

  console.log('Index deleted');
} catch (error) {
  console.error(error.message);
}
