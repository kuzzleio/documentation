try {
  await kuzzle.ms.bitop();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
