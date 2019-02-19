const {
  Kuzzle,
  WebSocket
} = require('kuzzle-sdk');
const kuzzle = new Kuzzle(new WebSocket('kuzzle'));
const credentials = { username: 'admin', password: 'test' };
const run = async () => {
  try {
    await kuzzle.connect();
    const jwt = await kuzzle.auth.login('local', credentials);
    console.log('You are now logged in!');
  } catch (error) {
    console.error(error);
  } finally {
    kuzzle.disconnect();
  }
};
run();