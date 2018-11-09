try {
  await kuzzle.ms.set('foo', 'bar');

  // Prints: 'bar'
  console.log(await kuzzle.ms.get('foo'));

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
