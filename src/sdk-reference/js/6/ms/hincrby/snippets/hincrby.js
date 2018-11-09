try {
  await kuzzle.ms.hincrby();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
