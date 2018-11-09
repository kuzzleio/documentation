try {
  await kuzzle.ms.hkeys();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
