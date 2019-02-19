try {
  await kuzzle.connect();
  // create a 'playground' index
  await kuzzle.index.create('playground');
  // create a collection named 'mycollection' in playground index
  await kuzzle.collection.create(
    'playground',
    'mycollection'
  );
  console.log('playground/mycollection ready');
} catch (error) {
  console.error(error.message);
}