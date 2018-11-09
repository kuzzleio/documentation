try {
  await kuzzle.ms.touch();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
