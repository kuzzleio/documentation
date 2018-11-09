try {
  await kuzzle.ms.pfmerge();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
