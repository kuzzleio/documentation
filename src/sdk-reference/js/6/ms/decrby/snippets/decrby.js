try {
  await kuzzle.ms.decrby();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
