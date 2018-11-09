try {
  await kuzzle.ms.setnx();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
