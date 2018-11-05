try {
  const serverInfo = await kuzzle.server.info();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
