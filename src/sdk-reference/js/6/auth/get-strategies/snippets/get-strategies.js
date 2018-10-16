try {
  await kuzzle.auth.login('local', {username: 'foo', password: 'bar'});
  await kuzzle.auth.getStrategies();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}