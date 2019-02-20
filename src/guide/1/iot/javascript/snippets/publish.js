const mqtt = require('mqtt');
const client = mqtt.connect({host: 'localhost'});
// Sending a volatile message
client.publish('Kuzzle/request', JSON.stringify({
  index: 'index',
  collection: 'collection',
  controller: 'realtime',
  action: 'publish',
  requestId: 'some_unique_id',
  body: { volatile: 'message' }
}));
