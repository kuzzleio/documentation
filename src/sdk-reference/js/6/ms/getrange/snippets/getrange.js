try {
  await kuzzle.ms.getrange();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
