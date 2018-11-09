try {
  await kuzzle.ms.lrem();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
