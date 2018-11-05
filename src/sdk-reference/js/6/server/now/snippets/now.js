try {
  const timestamp = await kuzzle.server.now();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
