function callback (notification) {
  if (notification.type === 'user') {
    console.log(notification);
    /*
    { status: 200,
      timestamp: 1539696822690,
      volatile: { sdkVersion: '6.0.0-beta-2', username: 'nina vkote' },
      index: 'nyc-open-data',
      collection: 'yellow-taxi',
      controller: 'realtime',
      action: 'subscribe',
      user: 'in',
      result: { count: 2 },
      type: 'user' }
    */
    console.log(`Currently ${notification.result.count} users in the room`);
  } else {
    console.log('Document notification');
  }
}

// instantiate a second kuzzle client because
// the same sdk instance does not receive his own notifications
const fuzzle = new Kuzzle('websocket', { host: 'kuzzle' });

try {
  const filters = { exists: 'name' };
  // Subscribe users notifications
  const options = { users: 'all' };

  const { roomId } = await kuzzle.realtime.subscribe('nyc-open-data', 'yellow-taxi', filters, callback, options);

  // Subscribe to the same room with the second client
  const opfions = { users: 'all', volatile: { username: 'nina vkote' } };
  await fuzzle.connect();
  await fuzzle.realtime.subscribe('nyc-open-data', 'yellow-taxi', filters, () => {}, opfions);

  await kuzzle.realtime.unsubscribe(roomId);
  fuzzle.disconnect();
} catch (error) {
  console.log(error.message);
}
