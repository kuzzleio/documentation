try {
  await kuzzle.ms.hset('hashfoo', 'almost_pi', 3);

  // Prints: 3.14159
  console.log(await kuzzle.ms.hincrbyfloat('hashfoo', 'almost_pi', .14159));

  console.log('Success');
} catch (error) {
  console.error(error.message);
}
