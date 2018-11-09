try {
  await kuzzle.ms.rpop();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
