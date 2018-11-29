function callback (notification) {
  if (notification.type === 'user') {
    console.log(notification.volatile);
    /*
      { sdkVersion: '<current SDK version>', username: 'nina vkote' },
    */
    console.log(`Currently ${notification.result.count} users in the room`);
  }
}

try {
  const filters = { exists: 'name' };
  // Subscribe users notifications
  const options = { users: 'all' };

  const roomId = await kuzzle.realtime.subscribe(
    'nyc-open-data',
    'yellow-taxi',
    filters,
    callback,
    options
  );

  // instantiate a second kuzzle client because
  // subscribing again to the same filters won't be considered
  // by Kuzzle as new users
  const kuzzle2 = new Kuzzle('websocket', { host: 'kuzzle' });
  await kuzzle2.connect();

  // Subscribe to the same room with the second client
  const options2 = { users: 'all', volatile: { username: 'nina vkote' } };
  await kuzzle2.realtime.subscribe(
    'nyc-open-data',
    'yellow-taxi',
    filters,
    () => {},
    options2
  );
} catch (error) {
  console.log(error.message);
}
