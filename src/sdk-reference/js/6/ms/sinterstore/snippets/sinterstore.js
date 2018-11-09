try {
  await kuzzle.ms.sinterstore();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
