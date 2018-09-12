kuzzle
  .<%= controller %>
  .<%= action %>()
  .then(() => {
    console.log('Success');
  })
  .catch(error => {
    console.error(error.message);
  });
