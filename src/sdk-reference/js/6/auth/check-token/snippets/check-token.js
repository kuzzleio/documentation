try {
  const jwt = await kuzzle.auth.login('local', {username: 'foo', password: 'bar'});
  const res = await kuzzle.auth.checkToken(jwt);
  if (res.valid) {
    console.log('Success');
  } else {
    console.err(res.state);
  }
} catch (error) {
  console.error(error.message);
}
