try {
  await kuzzle.ms.zrangebylex();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
