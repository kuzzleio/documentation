try {
  await kuzzle.ms.sunionstore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
