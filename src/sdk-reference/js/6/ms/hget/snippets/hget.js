try {
  await kuzzle.ms.hset('hashfoo', 'foo', 'bar');

  // Prints: 'bar'
  console.log(await kuzzle.ms.hget('hashfoo', 'foo'));

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
