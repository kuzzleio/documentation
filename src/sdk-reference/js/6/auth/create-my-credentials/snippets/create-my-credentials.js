await kuzzle.auth.login('local', {username: 'foo', password: 'bar'});
await kuzzle.auth.createMyCredentials('other', {username: 'foo', password: 'bar'});
console.log('Success');