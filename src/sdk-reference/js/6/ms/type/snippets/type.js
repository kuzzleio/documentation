try {
  await kuzzle.ms.type();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
