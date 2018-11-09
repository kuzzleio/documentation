try {
  await kuzzle.ms.zunionstore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
