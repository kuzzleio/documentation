try {
  await kuzzle.ms.rpoplpush();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
