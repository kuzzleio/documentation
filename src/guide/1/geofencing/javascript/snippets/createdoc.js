const bigBen = {
  lat: 51.510357,
  lon: -0.116773
};
const filter = {
  geoDistance: {
    location: bigBen,
    distance: '2km'
  }
};
const currentLocation = {
  firstName: 'Ada',
  lastName: 'Lovelace',
  location: bigBen
};
try {
// Create the user's location inside the circular area
  await kuzzle.document.create(
    'myindex',
    'mycollection',
    currentLocation,
    '326c8f08-63b0-429f-8917-b782d30930e'
  );
  console.log('document created');
} catch (error) {
  console.error(error.message);
}
