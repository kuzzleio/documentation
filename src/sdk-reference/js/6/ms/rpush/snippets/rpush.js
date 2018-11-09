try {
  await kuzzle.ms.rpush();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
