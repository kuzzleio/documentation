try {
  await kuzzle.ms.msetnx();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
