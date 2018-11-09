try {
  await kuzzle.ms.hmget();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
