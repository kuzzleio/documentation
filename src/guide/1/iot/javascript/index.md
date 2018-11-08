---
layout: full.html.hbs
algolia: true
title: Javascript
---

## IoT with Javascript

For this example we will use Node.js. You will need to install Node.js and NPM.

Let's create a new project folder called `iot`:


```bash
    mkdir iot
```

Now in you `iot` folder initialize the Node.js App:


```bash
    npm init
```

Give your project a name and set the version and any other details you want to configure.

For this code example we'll need the `mqtt` module. To install it, open the `package.json` file that was generated during the `npm init` and add the following dependency:


```javascript
/*...*/
"dependencies": {
    /*...*/
    "mqtt": "2.15.2"
},
```

Now install the `mqtt` module by running `npm install` in your `iot` folder:

```bash
    npm install
```

You should now see the `kuzzle-sdk` folder under `node_modules`.

Now the project configuration is complete, we can create an `index.js` file in the `iot` folder to program our test.

```bash
    touch index.js
```

## Connect to Kuzzle

The first thing we need to do is connect to Kuzzle. To do this write the following code:

```Javascript
//Get the mqtt module
var mqtt = require('mqtt');
//Connect to Kuzzle
client = mqtt.connect({host: 'localhost'});
```

Here we assume you have installed Kuzzle on your localhost, if this is not the case replace the `localhost` with the ip or name of the Kuzzle server.

## Subscribe to the MQTT Response Topic

Now that we have established a connection to Kuzzle, we will subscribe to the Kuzzle "Kuzzle/response" Topic so that the client can listen to responses from Kuzzle:

```Javascript
// Listen for the Kuzzle response
client.on('message', function(topic, raw){
    // Parse the message
    var message = JSON.parse(new Buffer(raw));
    // If this is a Kuzzle repsponse check the requestId
    if (topic === 'Kuzzle/response') {
        // Check if the requestId is the the same as for the request we sent
        if (message.requestId === 'unique_request_id') {
            console.log('Received Response: ', message);
            doSomething(message);
        }
    }
});
```

We have now programmed the subscription side of the MQTT transport.

## Publish a Request on the MQTT Request Topic

Now let's move on to the publish side of the test. Here we will publish a request to Kuzzle through the MQTT Protocol. In this case we will send a Collection Publish request.

```Javascript
client.on('connect', function() {
    //Once connected publish a message
    client.publish('Kuzzle/request', JSON.stringify({
        index: "myindex",
        collection: "mycollection",
        controller: 'realtime',
        action: 'publish',
        requestId: 'unique_request_id',
        body: {volatile: 'message'}
    }));
});
```

## Run the Test

The full code should look something like this:

```Javascript
/* Test Class */

//Get the mqtt module
var mqtt = require('mqtt');
//Connect to Kuzzle
client = mqtt.connect({host: 'localhost'});
client.on('connect', function() {
    //Once connected publish a message
    client.publish('Kuzzle/request', JSON.stringify({
        index: "myindex",
        collection: "mycollection",
        controller: 'realtime',
        action: 'publish',
        requestId: 'unique_request_id',
        body: {volatile: 'message'}
    }));
});

// Listen for the Kuzzle response
client.on('message', function(topic, raw){
    // Parse the message
    var message = JSON.parse(new Buffer(raw));
    // If this is a Kuzzle repsponse check the requestId
    if (topic === 'Kuzzle/response') {
        // Check if the requestId is the the same as for the request we sent
        if (message.requestId === 'unique_request_id') {
            console.log('Received Response: ', message);
            doSomething(message);
        }
    }
});


```

Create a test file using your favorite test framework. For a working example of this code refer to `javascript` folder in our snippet test [github repository](https://github.com/kuzzleio/kuzzle.io-snippet-tests). You can run the test using Visual Studio Code (using the launch.json configuration provided in the repository) or simply by executing the following command: 

```bash
    jasmine JASMINE_CONFIG_PATH=spec/runner/run_iot.json
```

The test script requires `jasmine`. If you don't already have it, you can install it with the following command:

```bash
    npm install -g jasmine
```
 
