try {
  await kuzzle.ms.sismember();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
