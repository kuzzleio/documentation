try {
  const specifications = await kuzzle.collection.getSpecifications('nyc-open-data', 'yellow-taxi');

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
