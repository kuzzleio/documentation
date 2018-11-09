try {
  await kuzzle.ms.set('val', 12);

  // Prints: 42
  console.log(await kuzzle.ms.decrby('val', -30));

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
