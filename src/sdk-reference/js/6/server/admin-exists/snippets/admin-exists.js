try {
  const admin = await kuzzle.server.adminExists();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
