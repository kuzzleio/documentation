try {
  await kuzzle.ms.ttl();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
