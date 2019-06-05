// Triggered whenever a document matching the filter is submitted to Kuzzle
const callback = (error, notification) => {
  if (error) {
    throw new Error(error);
  }
  console.log(
    'Something happened and we should do something.',
    notification
  );
};
try {
  await kuzzle.realtime.subscribe(
    'todo-list',
    'todos',
    {},
    callback
  );
  console.log('subscribe ok');
} catch (error) {
  console.error(error.message);
}