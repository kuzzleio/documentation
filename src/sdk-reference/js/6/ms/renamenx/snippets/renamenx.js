try {
  await kuzzle.ms.renamenx();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
