try {
  await kuzzle.ms.hset('hashfoo', 'key1', 'val1');
  await kuzzle.ms.hset('hashfoo', 'key2', 'val2');
  await kuzzle.ms.hset('hashfoo', 'key3', 'val3');

  // Prints: 3
  console.log(await kuzzle.ms.hlen('hashfoo'));

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
