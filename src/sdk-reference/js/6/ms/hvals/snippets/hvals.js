try {
  await kuzzle.ms.hvals();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
