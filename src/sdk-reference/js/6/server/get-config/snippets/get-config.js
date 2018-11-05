try {
  const config = await kuzzle.server.getConfig();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
