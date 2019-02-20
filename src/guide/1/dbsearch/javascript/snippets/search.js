// Search for documents with mountain as terrain property
const results = await kuzzle.document.search(
  'galaxies',
  'planets',
  {
    query: {
      match: {
        terrain: 'mountain'
      }
    }
  }
);

console.log(`There is ${results.hits.length} document that match.`);