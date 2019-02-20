const mqtt = require('mqtt');
const client = mqtt.connect({host: 'localhost'});
const channels = [];

// Sending a volatile message
client.publish('Kuzzle/request', JSON.stringify({
  index: 'index',
  collection: 'collection',
  controller: 'realtime',
  action: 'subscribe',
  requestId: 'some_unique_id',
  body: {
  }
}));

// Getting Kuzzle's response
client.on('message', (topic, raw) => {
  const message = JSON.parse(Buffer.from(raw));
  // API results topic
  if (topic === 'Kuzzle/response') {
    // Response to our "publish" request
    if (message.requestId === 'some_unique_id') {
      channels.push(message.result.channel);
      client.subscribe(message.result.channel);
    }
  }
  else if (channels.indexOf(topic) !== -1) {
    // Subscription notification
    console.log('Notification: ', message.result._source.volatile);
  }
});
