try {
  await kuzzle.ms.getset();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
