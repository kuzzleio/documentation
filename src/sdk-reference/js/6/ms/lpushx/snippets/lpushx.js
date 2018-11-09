try {
  await kuzzle.ms.lpushx();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
