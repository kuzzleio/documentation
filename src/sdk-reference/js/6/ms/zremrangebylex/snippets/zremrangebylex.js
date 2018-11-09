try {
  await kuzzle.ms.zremrangebylex();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
