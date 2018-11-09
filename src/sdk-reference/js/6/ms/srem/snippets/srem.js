try {
  await kuzzle.ms.srem();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
