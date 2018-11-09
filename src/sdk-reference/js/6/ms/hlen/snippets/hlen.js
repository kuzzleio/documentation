try {
  await kuzzle.ms.hlen();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
