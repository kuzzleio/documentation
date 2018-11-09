try {
  await kuzzle.ms.zrem();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
