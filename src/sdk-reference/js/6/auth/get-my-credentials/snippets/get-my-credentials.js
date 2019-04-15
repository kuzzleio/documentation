const credentials = { username: 'foo', password: 'bar' };

try {
  await kuzzle.auth.login('local', credentials);

  const localCredentials = await kuzzle.auth.getMyCredentials('local');
  console.log(localCredentials);
  /*
    {  username: 'foo', kuid: '<user unique identifier>' }
  */

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
