try {
  const admin = await kuzzle.server.adminExists();
  console.log('Admin exists?', admin);
} catch (error) {
  console.error(error.message);
}
