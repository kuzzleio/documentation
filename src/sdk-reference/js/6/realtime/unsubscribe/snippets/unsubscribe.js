function callback (notification) {}

try {
  const { roomId } = await kuzzle.realtime.subscribe('nyc-open-data', 'yellow-taxi', {}, callback);

  await kuzzle.realtime.unsubscribe(roomId);

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
