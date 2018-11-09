try {
  await kuzzle.ms.sdiffstore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
