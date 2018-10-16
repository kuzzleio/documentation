function callback (notification) {
  if (notification.scope === 'in') {
    console.log(`${notification.result._source.name} enter the scope`);
  } else {
    console.log(`${notification.result._source.name} moved from the scope`);
  }
}

try {
  // Subscribe to notifications for documents containing a 'name' property
  const filters = { exists: 'name' };

  const { roomId } = await kuzzle.realtime.subscribe('nyc-open-data', 'yellow-taxi', {}, callback);
  console.log(roomId);

  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', null, { name: 'nina vkote' });
} catch (error) {
  console.log(error.message);
}
