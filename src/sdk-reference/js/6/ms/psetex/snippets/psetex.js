try {
  await kuzzle.ms.psetex();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
