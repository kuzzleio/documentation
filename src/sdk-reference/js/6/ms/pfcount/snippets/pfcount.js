try {
  await kuzzle.ms.pfcount();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
