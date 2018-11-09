try {
  await kuzzle.ms.ping();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
