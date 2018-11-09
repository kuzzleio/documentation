try {
  await kuzzle.ms.sunion();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
