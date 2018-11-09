try {
  await kuzzle.ms.zremrangebyscore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
