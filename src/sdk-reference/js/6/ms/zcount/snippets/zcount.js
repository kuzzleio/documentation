try {
  await kuzzle.ms.zcount();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
