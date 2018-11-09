try {
  await kuzzle.ms.zincrby();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
