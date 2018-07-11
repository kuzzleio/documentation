kuzzle
  .index
  .refreshInternal()
  .then(response => {
    if (response.acknowledged) {
      console.log('Internal index successfully refreshed')
    } else {
      console.log('Can not refresh internal index')
    }
  })
  .catch(error => {
    console.error(error.message)
  });
