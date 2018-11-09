try {
  await kuzzle.ms.hset();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
