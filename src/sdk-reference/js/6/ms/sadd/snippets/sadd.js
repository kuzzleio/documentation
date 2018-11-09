try {
  await kuzzle.ms.sadd();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
