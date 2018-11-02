function callback (notification) {}

try {
  const roomId = await kuzzle.realtime.subscribe('nyc-open-data', 'yellow-taxi', {}, callback);

  const count = await kuzzle.realtime.count(roomId);

  console.log(`Currently ${count} active subscription`);
} catch (error) {
  console.error(error.message);
}
