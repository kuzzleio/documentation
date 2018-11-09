try {
  await kuzzle.ms.zremrangebyrank();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
