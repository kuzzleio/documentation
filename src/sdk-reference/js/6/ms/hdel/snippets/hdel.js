try {
  await kuzzle.ms.hdel();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
