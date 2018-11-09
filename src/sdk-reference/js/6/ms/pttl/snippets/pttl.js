try {
  await kuzzle.ms.pttl();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
