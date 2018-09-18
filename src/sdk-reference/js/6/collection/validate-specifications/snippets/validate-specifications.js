try {
  const specifications = {
    'nyc-open-data': {
      'yellow-taxi': {
        strict: true,
        fields: {
          license: {
            mandatory: true,
            type: 'symbol' // symbol is not a recognized type
          }
        }
      }
    }
  };

  const result = await kuzzle.collection.validateSpecifications(specifications);
  /*
  {
    valid: false,
    details: [
      'In nyc-open-data.yellow-taxi.license: symbol is not a recognized type.'
    ],
    description: 'Some errors with provided specifications.'
  }
  */
  if (result.valid === false) {
    console.log('Success');
  }
} catch (error) {
  console.error(error.message);
}
