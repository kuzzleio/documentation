try {
  await kuzzle.ms.zscore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
