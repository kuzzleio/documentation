try {
  await kuzzle.ms.geodist();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
