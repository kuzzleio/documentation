try {
  await kuzzle.ms.zlexcount();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
