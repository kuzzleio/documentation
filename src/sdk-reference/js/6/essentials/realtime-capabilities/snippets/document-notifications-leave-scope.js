function callback (notification) {
  console.log(notification);
  /*
  {
    status: 200,
    requestId: '82b61a33-1015-4271-a02f-91eb68024384',
    timestamp: 1539616825163,
    volatile: { sdkVersion: '6.0.0-beta-2' },
    index: 'nyc-open-data',
    collection: 'yellow-taxi',
    controller: 'document',
    action: 'create',
    scope: 'in',
    state: 'done',
    result:
     { _source: {
          age: 19,
          _kuzzle_info:
          { author: '-1',
            createdAt: 1539616825164,
            updatedAt: null,
            updater: null,
            active: true,
            deletedAt: null }
        },
       _id: 'nina-vkote' },
    type: 'document'
  }
  */
  console.log(`Document with age = ${notification.result._source.age} moved from the scope`);
}

try {
  // Subscribe to notifications when document leaves the scope
  const filters = { range: { name: { gte: 19 } } };
  const options = { scope: 'out' };

  const { roomId } = await kuzzle.realtime.subscribe('nyc-open-data', 'yellow-taxi', {}, callback);
  console.log(roomId);

  await kuzzle.document.create('nyc-open-data', 'yellow-taxi', 'nina-vkote', { age: 19 });
  // The document isn't in the scope anymore
  await kuzzle.document.update('nyc-open-data', 'yellow-taxi', 'nina-vkote', { age: 42 });
} catch (error) {
  console.log(error.message);
}
