try {
  await kuzzle.ms.strlen();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
