const results = await kuzzle.document.search(
  'chat',
  'messages',
  {} // leave body empty to match all documents
);
