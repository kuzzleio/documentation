try {
  await kuzzle.ms.hstrlen();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
