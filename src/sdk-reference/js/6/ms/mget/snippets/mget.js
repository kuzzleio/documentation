try {
  await kuzzle.ms.mget();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
