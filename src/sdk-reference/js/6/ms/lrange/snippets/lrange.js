try {
  await kuzzle.ms.lrange();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
