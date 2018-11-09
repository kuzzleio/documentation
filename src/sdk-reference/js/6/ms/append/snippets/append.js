try {
  await kuzzle.ms.append();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
