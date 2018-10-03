try {
  await kuzzle.document.mGet();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}