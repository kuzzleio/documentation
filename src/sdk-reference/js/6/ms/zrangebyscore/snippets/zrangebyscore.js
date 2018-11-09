try {
  await kuzzle.ms.zrangebyscore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
