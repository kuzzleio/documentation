try {
  await kuzzle.<%= controller %>.<%= action %>()
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
