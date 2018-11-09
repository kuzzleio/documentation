try {
  await kuzzle.ms.del();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
