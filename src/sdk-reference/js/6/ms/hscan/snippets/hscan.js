try {
  await kuzzle.ms.hscan();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
