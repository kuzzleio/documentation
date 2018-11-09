try {
  await kuzzle.ms.expireat();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
