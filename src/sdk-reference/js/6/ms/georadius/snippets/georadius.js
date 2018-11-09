try {
  await kuzzle.ms.georadius();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
