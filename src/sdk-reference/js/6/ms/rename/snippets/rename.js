try {
  await kuzzle.ms.rename();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
