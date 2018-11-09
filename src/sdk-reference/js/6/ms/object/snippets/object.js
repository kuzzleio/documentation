try {
  await kuzzle.ms.object();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
