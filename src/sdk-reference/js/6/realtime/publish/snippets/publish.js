const message = { realtime: 'rule the web' };

try {
  const published = await kuzzle.realtime.publish('i-dont-exist', 'in-database', message);

  if (published) {
    console.log('Success');
  }
} catch (error) {
  console.error(error.message);
}
