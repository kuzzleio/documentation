try {
  await kuzzle.ms.smembers();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
