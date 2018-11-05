try {
  const lastStat = await kuzzle.server.getLastStats();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
