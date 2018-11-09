try {
  await kuzzle.ms.lindex();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
