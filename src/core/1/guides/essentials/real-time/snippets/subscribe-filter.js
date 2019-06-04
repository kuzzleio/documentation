// Create a filter that defines a regexp
const filter = {regexp: {label: 'URGENT'}};
// Triggered whenever a document matching the filter is submitted to Kuzzle
const callback = (error, notification) => {
  if (error) {
    throw new Error(error);
  }
  console.log(
    'Something happened and we should do something URGENTLY.',
    notification
  );
};
try {
  await kuzzle.realtime.subscribe(
    'todo-list',
    'todos',
    filter,
    callback
  );
  console.log('subscribe ok');
} catch (error) {
  console.error(error.message);
}