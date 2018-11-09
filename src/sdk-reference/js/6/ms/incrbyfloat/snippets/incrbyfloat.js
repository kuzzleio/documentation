try {
  await kuzzle.ms.incrbyfloat();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
