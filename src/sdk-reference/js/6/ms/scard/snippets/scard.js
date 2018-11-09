try {
  await kuzzle.ms.scard();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
