try {
  await kuzzle.ms.lset();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
