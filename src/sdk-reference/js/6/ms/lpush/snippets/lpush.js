try {
  await kuzzle.ms.lpush();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
