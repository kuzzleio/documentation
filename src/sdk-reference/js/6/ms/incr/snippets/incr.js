try {
  await kuzzle.ms.incr();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
