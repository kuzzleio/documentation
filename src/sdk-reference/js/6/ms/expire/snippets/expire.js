try {
  await kuzzle.ms.expire();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
