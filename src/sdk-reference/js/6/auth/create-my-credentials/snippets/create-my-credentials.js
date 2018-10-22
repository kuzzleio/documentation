const credentials = { username: 'foo', password: 'bar' };

await kuzzle.auth.login('local', credentials);

await kuzzle.auth.createMyCredentials('other', credentials);

console.log('Success');
