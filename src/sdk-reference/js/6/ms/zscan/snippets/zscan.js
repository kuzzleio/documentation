try {
  await kuzzle.ms.zscan();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
