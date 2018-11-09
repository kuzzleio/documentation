try {
  await kuzzle.ms.set();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
