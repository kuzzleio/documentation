try {
  await kuzzle.ms.getbit();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
