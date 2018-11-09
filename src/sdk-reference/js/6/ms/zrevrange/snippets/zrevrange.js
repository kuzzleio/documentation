try {
  await kuzzle.ms.zrevrange();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
