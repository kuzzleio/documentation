try {
  await kuzzle.ms.scan();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
