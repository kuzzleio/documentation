try {
  await kuzzle.ms.set('foo', '\x01');

  // Prints: 0
  console.log(await kuzzle.ms.getbit('foo', 2));

  // Prints: 1
  console.log(await kuzzle.ms.getbit('foo', 7));

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
