try {
  await kuzzle.ms.linsert();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
