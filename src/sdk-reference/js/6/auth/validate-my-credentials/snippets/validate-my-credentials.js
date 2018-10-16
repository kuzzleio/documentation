try {
  await kuzzle.auth.login('local', {username: 'foo', password: 'bar'});
  kuzzle.auth.validateMyCredentials();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
