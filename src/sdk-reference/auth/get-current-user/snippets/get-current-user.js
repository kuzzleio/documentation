kuzzle
  .auth
  .getCurrentUser()
  .then((currentUser) => {
    console.log(currentUser._id)
  })
  .catch(error => {
    console.error(error.message)
  });
