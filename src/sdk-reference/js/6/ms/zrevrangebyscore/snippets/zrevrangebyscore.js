try {
  await kuzzle.ms.zrevrangebyscore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
