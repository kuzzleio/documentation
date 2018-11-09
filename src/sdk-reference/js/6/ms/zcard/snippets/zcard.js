try {
  await kuzzle.ms.zcard();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
