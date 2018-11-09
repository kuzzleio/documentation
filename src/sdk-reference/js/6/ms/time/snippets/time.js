try {
  await kuzzle.ms.time();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
