try {
  await kuzzle.ms.zinterstore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
