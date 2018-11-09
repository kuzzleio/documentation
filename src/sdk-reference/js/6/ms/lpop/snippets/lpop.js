try {
  await kuzzle.ms.lpop();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
