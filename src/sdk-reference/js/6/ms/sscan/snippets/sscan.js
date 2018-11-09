try {
  await kuzzle.ms.sscan();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
