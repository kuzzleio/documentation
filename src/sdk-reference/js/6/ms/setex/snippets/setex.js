try {
  await kuzzle.ms.setex();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
