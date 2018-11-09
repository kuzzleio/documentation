try {
  await kuzzle.ms.set('hw', 'Hello');

  await kuzzle.ms.append('hw', ' World');

  // Prints: Hello World
  console.log(await kuzzle.ms.get('hw'));

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
