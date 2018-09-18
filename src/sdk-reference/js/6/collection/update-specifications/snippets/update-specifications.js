try {
  const specifications = {
    'nyc-open-data': {
      'yellow-taxi': {
        strict: false,
        fields: {
          license: {
            mandatory: true,
            type: 'string'
          }
        }
      }
    }
  };

  const result = await kuzzle.collection.updateSpecifications('nyc-open-data', 'yellow-taxi', specifications);

  if (result['nyc-open-data'] !== undefined) {
    console.log('Success');
  }
} catch (error) {
  console.error(error.message);
}
