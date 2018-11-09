try {
  await kuzzle.ms.exists();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
