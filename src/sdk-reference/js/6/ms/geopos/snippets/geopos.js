try {
  await kuzzle.ms.geopos();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
