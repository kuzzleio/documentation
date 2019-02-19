const {
  Kuzzle,
  WebSocket
} = require('kuzzle-sdk');
const kuzzle = new Kuzzle(new WebSocket('kuzzle'));
const bigBen = {
  lat: 51.510357,
  lon: -0.116773
};
// Create a filter that defines the circular area around Big Ben
const filter = {
  geoDistance: {
    location: bigBen,
    distance: '2km'
  }
};
// Create the user's location: they are inside the circular area
const currentLocation = {
  firstName: 'Ada',
  lastName: 'Lovelace',
  location: bigBen
};
const hydePark = {
  lat: 11.507268,
  lon: -0.165730
};
const newLocation = {location: hydePark};
const run = async () => {
  const callback = async (notification) => {
    if (notification.scope === 'out') {
      console.log('User has entered Big Ben');
      kuzzle.disconnect();
    }
  };
  try {
    await kuzzle.connect();
    /* Creates a subscription that triggers a notification
    when a user enters the circular area */
    await kuzzle.realtime.subscribe(
      'myindex',
      'mycollection',
      filter,
      callback
    );
    // Create the user's location inside the circular area
    await kuzzle.document.create(
      'myindex',
      'mycollection',
      currentLocation,
      '326c8f08-63b0-429f-8917-b782d30930e'
    );
    // Update the user's location: now they are outside the circular area
    await kuzzle.document.update(
      'myindex',
      'mycollection',
      '326c8f08-63b0-429f-8917-b782d30930e',
      newLocation
    );
  } catch (error) {
    console.error(error.message);
  }
};
run();