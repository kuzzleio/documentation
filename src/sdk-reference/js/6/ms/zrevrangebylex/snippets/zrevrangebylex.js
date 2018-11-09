try {
  await kuzzle.ms.zrevrangebylex();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
