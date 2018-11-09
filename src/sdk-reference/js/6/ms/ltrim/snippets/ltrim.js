try {
  await kuzzle.ms.ltrim();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
