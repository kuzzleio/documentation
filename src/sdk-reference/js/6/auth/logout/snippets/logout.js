try {
  await kuzzle.auth.login('local', {username: 'foo', password: 'bar'});
  await kuzzle.auth.logout();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
