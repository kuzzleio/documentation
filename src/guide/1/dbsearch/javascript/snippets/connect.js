//Require and instanciate kuzzle
const {
  Kuzzle,
  WebSocket
} = require('kuzzle-sdk');
    
const kuzzle = new Kuzzle(
  new WebSocket('kuzzle')
);
    
const run = async () => {
  try {
    //Wait for etablished connection to Kuzzle
    await kuzzle.connect();

    console.log('Successfully connected to Kuzzle!');
  } catch (error) {
    console.error(error.message);
  } finally {
    //Disconnecting kuzzle
    kuzzle.disconnect();
  }
};
    
run();