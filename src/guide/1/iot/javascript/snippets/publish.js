const mqtt = require('/usr/local/lib/node_modules/mqtt');
const client = mqtt.connect({host: 'localhost'});
// Sending a volatile message
try {
  client.publish('Kuzzle/request', JSON.stringify({
    index: 'index',
    collection: 'collection',
    controller: 'realtime',
    action: 'publish',
    requestId: 'some_unique_id',
    body: { volatile: 'message' }
  }));
} catch (error) {
  console.log(error.message);
} finally {
  client.end();
}
