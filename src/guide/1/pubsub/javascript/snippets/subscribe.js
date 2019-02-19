// Creates a filter that defines that the 'message' field exists
const filter = {exists: {field: 'message'}};
// Will be triggered each time a document matches the filter
const callback = (notification) => {
  console.log(notification.result._source.message);
};
try {
  await kuzzle.realtime.subscribe(
    'myindex',
    'mycollection',
    filter,
    callback
  );
  console.log('subscribe ok');
} catch (error) {
  console.error(error.message);
}