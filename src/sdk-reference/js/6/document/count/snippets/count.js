try {
  const count = await kuzzle.document.count(
    'nyc-open-data',
    'yellow-taxi',
    { query: { match: { licence: 'valid' } } }
  );

  console.log(`Found ${count} documents matching licence:valid`);
} catch (error) {
  console.error(error.message);
}
