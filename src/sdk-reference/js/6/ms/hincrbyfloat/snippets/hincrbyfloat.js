try {
  await kuzzle.ms.hincrbyfloat();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
