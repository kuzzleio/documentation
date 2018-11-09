try {
  await kuzzle.ms.decr();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
