try {
  await kuzzle.ms.incrby();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
