try {
  await kuzzle.ms.zrank();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
