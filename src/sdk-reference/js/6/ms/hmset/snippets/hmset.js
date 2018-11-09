try {
  await kuzzle.ms.hmset();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
