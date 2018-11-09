try {
  await kuzzle.ms.spop();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
