kuzzle.index
  .refreshInternal()
  .then(success => {
    if (success) {
      console.log('Internal index successfully refreshed');
    } else {
      console.log('Can not refresh internal index');
    }
  })
  .catch(error => {
    console.error(error.message);
  });
