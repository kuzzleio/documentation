// Delete the galaxies index if exists
if (await kuzzle.index.exists('galaxies')) {
  await kuzzle.index.delete('galaxies');
}

// Create galaxies index, planets collection and 2 documents
// with different terrain property
await kuzzle.index.create('galaxies');
await kuzzle.collection.create('galaxies', 'planets');
await kuzzle.document.create(
  'galaxies',
  'planets',
  { terrain: 'mountain' }
);
await kuzzle.document.create(
  'galaxies',
  'planets',
  { terrain: 'other' }
);
console.log('New documents successfully created!');
