try {
  await kuzzle.ms.zrange();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
