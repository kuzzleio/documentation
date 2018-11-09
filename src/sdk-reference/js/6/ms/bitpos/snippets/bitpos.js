try {
  await kuzzle.ms.bitpos();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
