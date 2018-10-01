try {
  const deleted = await kuzzle.document.deleteByQuery(
    'nyc-open-data',
    'yellow-taxi',
    {
      query: {
        match: {capacity: 7}
      }
    }
  );

  console.log('Success');
} catch (error) {
  console.error(error.message);
}