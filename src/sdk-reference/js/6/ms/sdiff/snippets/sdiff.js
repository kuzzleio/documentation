try {
  await kuzzle.ms.sdiff();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
