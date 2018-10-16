function callback (notification) {
  console.log(notification);
  /*
  { status: 200,
    requestId: 'b70997aa-682d-4beb-8d97-4dc5a786a799',
    timestamp: 1539678495284,
    volatile: { sdkVersion: '6.0.0-beta-2' },
    index: 'nyc-open-data',
    collection: 'yellow-taxi',
    controller: 'document',
    action: 'update',
    scope: 'out',
    result: { _id: 'AWZ7_WIoJD41ulNI_b-V' },
    type: 'document' }
  */
  console.log(`Document moved ${notification.scope} from the scope`);
}

try {
  // Subscribe to notifications when document leaves the scope
  const filters = { range: { age: { lte: 20 } } };
  const options = { scope: 'out' };

  await kuzzle.realtime.subscribe('nyc-open-data', 'yellow-taxi', filters, callback, options);

  const document = { name: 'nina vkote', age: 19 };

  // The document is in the scope
  const { _id } = await kuzzle.document.create('nyc-open-data', 'yellow-taxi', null, document);

  // The document isn't in the scope anymore
  await kuzzle.document.update('nyc-open-data', 'yellow-taxi', _id, { age: 42 });
} catch (error) {
  console.log(error.message);
}
