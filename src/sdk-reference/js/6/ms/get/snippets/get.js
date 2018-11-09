try {
  await kuzzle.ms.get();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
