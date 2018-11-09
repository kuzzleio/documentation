try {
  await kuzzle.ms.keys();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
