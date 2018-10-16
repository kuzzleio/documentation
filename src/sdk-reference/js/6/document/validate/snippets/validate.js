try {
  const isValid = await kuzzle.document.validate('nyc-open-data', 'yellow-taxi', {
    capacity: 4
  });

  if (isValid) {
    console.log('Success');
  }
} catch (error) {
  console.error(error.message);
}