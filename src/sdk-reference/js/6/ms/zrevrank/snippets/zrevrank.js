try {
  await kuzzle.ms.zrevrank();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
