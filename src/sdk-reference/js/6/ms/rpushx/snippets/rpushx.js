try {
  await kuzzle.ms.rpushx();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
