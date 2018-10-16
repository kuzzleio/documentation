try {
  await kuzzle.auth.login('local', {username: 'foo', password: 'bar'});
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
