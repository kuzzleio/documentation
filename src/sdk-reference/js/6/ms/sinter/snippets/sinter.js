try {
  await kuzzle.ms.sinter();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
