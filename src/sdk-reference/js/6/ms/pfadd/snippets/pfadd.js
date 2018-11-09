try {
  await kuzzle.ms.pfadd();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
