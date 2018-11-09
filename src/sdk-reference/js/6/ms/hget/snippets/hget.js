try {
  await kuzzle.ms.hget();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
