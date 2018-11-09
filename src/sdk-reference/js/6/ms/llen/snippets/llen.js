try {
  await kuzzle.ms.llen();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
