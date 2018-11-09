try {
  await kuzzle.ms.bitcount();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
