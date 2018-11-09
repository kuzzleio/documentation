try {
  await kuzzle.ms.sort();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
