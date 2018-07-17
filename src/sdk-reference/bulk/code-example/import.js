kuzzle.bulk
  .import(
    [
      { create: { _id: '1', _index: 'nyc-open-data', _type: 'bulkimport' } },
      { a: 'document', with: 'any', number: 'of fields' },
      { create: { _id: '2', _index: 'nyc-open-data', _type: 'bulkimport' } },
      { another: 'document' },
      { create: { _id: '3', _index: 'nyc-open-data', _type: 'bulkimport' } },
      { and: { another: 'one' } }
    ],
    {}
  )
  .then(response => {
    console.log(`Successfully imported ${response.items.length} documents`);
  })
  .catch(error => {
    console.error(error.message);
  });
