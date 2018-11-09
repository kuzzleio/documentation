try {
  await kuzzle.ms.mset();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
