//Require and instanciate kuzzle
const {
  Kuzzle,
  WebSocket
} = require('kuzzle-sdk');
  
const kuzzle = new Kuzzle(
  new WebSocket('localhost')
);
  
const run = async () => {
  try {
    //Wait for etablished connection to Kuzzle
    await kuzzle.connect();
  
    //Delete the galaxies index if exists
    if (await kuzzle.index.exists('galaxies')) {
      await kuzzle.index.delete('galaxies');
    }
          
    //Create galaxies index, planets collection and 2 documents 
    //with different terrain property
    await kuzzle.index.create('galaxies');
    await kuzzle.collection.create('galaxies', 'planets');
    await kuzzle.document.create(
      'galaxies',
      'planets',
      { terrain: 'mountain' },
    );
    await kuzzle.document.create(
      'galaxies',
      'planets',
      { terrain: 'other' },
    );
    console.log('New documents successfully created!');
  } catch (error) {
    console.error(error.message);
  } finally {
    //Disconnecting kuzzle
    kuzzle.disconnect();
  }
};
  
run();