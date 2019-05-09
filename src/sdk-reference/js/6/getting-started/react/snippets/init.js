const exists = await kuzzle.index.exists('chat');
if (!exists) {
  await kuzzle.index.create('chat');
  await kuzzle.collection.create('chat', 'messages');
}
