try {
  await kuzzle.ms.geohash();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
