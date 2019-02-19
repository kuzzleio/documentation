const bigBen = {
  lat: 51.510357,
  lon: -0.116773
};
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
try {
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
  console.log('document updated');
} catch (error) {
  console.error(error.message);
}