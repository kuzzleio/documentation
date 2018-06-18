// create a 'playground' index and then a collection named 'mycollection' that we can use to store data
kuzzle
  .createIndexPromise('playground')
  .then(() => kuzzle.collection('mycollection').createPromise())
  .then(() => {
    console.log('bar')
  })
  .catch(err => {
    console.error(err.message)
  })
  .finally(() => kuzzle.disconnect())