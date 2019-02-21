---
layout: full.html.hbs
title: Javascript
---

## IoT with Javascript

For this example we will use Node.js. You will need to install Node.js and NPM.

Let's create a new project folder called `iot` and install mqtt module:


```bash
mkdir iot
cd iot
npm install mqtt
```

Now the project configuration is complete, we can create a `subscribe.js` and a `publish.js` files in the `iot` folder to program our example.

```bash
touch subscribe.js publish.js 
```

## Connect to Kuzzle

In both files, the first thing we need to do is connect to Kuzzle. To do this add the following code:

```Javascript
const
  //Get the mqtt module
  mqtt = require('mqtt'),
  //Connect to Kuzzle
  client = mqtt.connect({ host: 'localhost' });
```

Here we assume you have installed Kuzzle on your localhost, if this is not the case replace the `localhost` with the ip or name of the Kuzzle server.

## Publish a Request on the MQTT Request Topic

Now let's move on to the publish side of the test. Here we will publish a request to Kuzzle through the MQTT Protocol. In this case we will send a Collection Publish request. You can add the following code to your `publish.js` file

```Javascript
// Sending a volatile message
client.publish('Kuzzle/request', JSON.stringify({
    index: 'devices',
    collection: 'sensors',
    controller: 'realtime',
    action: 'publish',
    requestId: 'some-uniq-id',
    body: { 
      command: 'battery-report' 
    }
  }));
```

## Subscribe to the MQTT Response Topic

Now we will subscribe to the Kuzzle `Kuzzle/response` so that the client can listen to published messages.
You should add a `channels` array to save the subscriptions:

```Javascript
const channels = [];
```

We need to know the channel id to subscribe. After published a message we get the channel id as response so you can add the previous code in the `subscribe.js` file.
After that, you could add the following code to do the subscription:

```Javascript
// Getting Kuzzle's response
client.on('message', (topic, raw) => {
  const message = JSON.parse(Buffer.from(raw));
  // API results topic
  if (topic === 'Kuzzle/response') {
    // Response to our "publish" request
    if (message.requestId === 'some-uniq-id' && message.result && message.result.channel) {
      channels.push(message.result.channel);
      client.subscribe(message.result.channel);
    }
  }
  else if (channels.indexOf(topic) !== -1) {
    // Subscription notification
    console.log('Notification: ', message);
  }
});
```

We have now programmed the subscription side of the MQTT transport.

## Run the Example

The full code of your `publish.js` file should look something like this:

[snippet=publish]

And that of your `subscribe.js` file should look something like this:

[snippet=subscribe]

Now, run the following command in a terminal. The notifications of published messages will appear here.

```bash
node subscribe.js
```

Run the following command in another terminal and return to your other terminal to see the notification.

```bash
node publish.js
```
