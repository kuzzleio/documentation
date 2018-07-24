kuzzle
  .{{controller}}
  .{{action}}()
  .then(response => {
    console.log('{{success_message}}')
  })
  .catch(error => {
    console.error(error.message)
  });
