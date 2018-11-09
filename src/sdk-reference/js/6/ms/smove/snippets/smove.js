try {
  await kuzzle.ms.smove();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
