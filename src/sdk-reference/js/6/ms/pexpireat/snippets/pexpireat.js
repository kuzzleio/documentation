try {
  await kuzzle.ms.pexpireat();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
