try {
  const stats = await kuzzle.server.getStats(
    '1234567890101',
    '1541426610304',
    null
  );
  console.log('Success');
} catch (error) {
  console.error(error.message);
}
