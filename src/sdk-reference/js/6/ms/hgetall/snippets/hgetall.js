try {
  await kuzzle.ms.hgetall();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
