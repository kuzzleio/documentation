try {
  await kuzzle.ms.georadiusbymember();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
