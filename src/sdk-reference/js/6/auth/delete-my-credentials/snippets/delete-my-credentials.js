try {
  const jwt = await kuzzle.auth.login('local', {username: 'foo', password: 'bar'});
  await kuzzle.auth.deleteMyCredentials('local');
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
