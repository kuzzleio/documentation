try {
  await kuzzle.ms.geoadd();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
