try {
  await kuzzle.ms.pexpire();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
