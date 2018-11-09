try {
  await kuzzle.ms.hexists();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
