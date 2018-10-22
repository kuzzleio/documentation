const credentials = { username: 'foo', password: 'bar' };

try {
  const jwt = await kuzzle.auth.login('local', credentials);
  
  const result = await kuzzle.auth.checkToken(jwt);

  if (result.valid) {
    console.log('Success');
  } else {
    console.error(result.state);
  }
} catch (error) {
  console.error(error.message);
}
