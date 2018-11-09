try {
  await kuzzle.ms.persist();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
