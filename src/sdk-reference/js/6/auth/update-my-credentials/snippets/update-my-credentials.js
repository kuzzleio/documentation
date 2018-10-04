try {
  await kuzzle.auth.login('local', {username: 'foo', password: 'bar'});
  await kuzzle.auth.updateMyCredentials('local', {username: 'foo', password: 'bar', other: 'value'});
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
