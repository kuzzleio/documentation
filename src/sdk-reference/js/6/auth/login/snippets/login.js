const credentials = { username: 'foo', password: 'bar' };

try {
  await kuzzle.auth.login('local', credentials);

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
