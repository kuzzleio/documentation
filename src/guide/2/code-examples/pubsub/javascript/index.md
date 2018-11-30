---
layout: full.html.hbs
title: Javascript
---

## Pub/Sub with Javascript

For this example we will use Node.js. You will need to install Node.js and NPM.

Let's create a new project folder called `realtimePubSub`:


```bash
    mkdir realtimePubSub
```

Now in you `realtimePubSub` folder initialize the Node.js App:


```bash
    npm init
```

Give your project a name and set the version and any other details you want to configure.

For this code example we'll need Kuzzle's Javascript SDK. To install it, open the `package.json` file that was generated during the `npm init` and add the following dependency:


```javascript
/*...*/
"dependencies": {
    /*...*/
    "kuzzle-sdk": "5.0.11"
},
```

Now install the Kuzzle Javascript SDK by running `npm install` in your `realtimePubSub` folder:

```bash
    npm install
```

You should now see the `kuzzle-sdk` folder under `node_modules`.

Now the project configuration is complete, we can create an `index.js` file in the `realtimePubSub` folder to program our test.

```bash
    touch index.js
```

## Connect to Kuzzle

The first thing we need to do is connect to Kuzzle. To do this write the following code:

```Javascript
var kuzzle  = new Kuzzle("localhost");
```

Here we assume you have installed Kuzzle on your localhost, if this is not the case replace the `localhost` with the ip or name of the Kuzzle server.

## Subscribe to Documents with Specific Criteria


Let's create a *subscribe* method where we will program the subscription side of the test:

```Javascript
function subscribe() {
    /* TODO */
}
```

We will perform a subscription request that tells Kuzzle that the App wants to be notified anytime a document is created that contains the *message* field. We define this subscription filter as follows, for more information about filters click [here]({{ site_base_path }}kuzzle-dsl/):

```Javascript
function subscribe() {
    var filter = {exists: {field: "message"}};
}
```

Use the Collection `subscribe` method to execute the subscription request, using the filter object as input.

```Javascript
function subscribe() {
    kuzzle
    .collection("mycollection", "myindex")
    .subscribe(filter, function(error, notification) {
        //called each time a message is received
        if(error) handleError(error); 
        else doSomething(notification);
    })
}
```

Since we are also going to publish a message from the same App, we will want to know when the subscription is successful so that we can then publish the the  message. We can do this by using the `.onDone()` method on our subscription request:

```Javascript
function subscribe() {
    kuzzle
    .collection("mycollection", "myindex")
    .subscribe(filter, function(error, notification) {
        if(error) handleError(error);
        else doSomething(notification);
    })
    .onDone(function(error, room) {
        /* we are now subscribed to the room */
        publish();
    });
}
```

We have now programmed the subscription side of the test.


## Publish a Document

Now let's move on to the publish side of the test. Here we will publish a document that contains the `message` field. When Kuzzle receives this message, it will detect that there is a subscriber listening for such messages and will send it to these subscribers, in this case to our Android App.

We will program a *publish* method that connects to Kuzzle and creates a document that contains the value `hello world` in the `message` field.

```Javascript
function publish(){
    kuzzle
    .collection("mycollection", "myindex")
    .publishMessage({message: "hello world"});
}
```
 
## Run the Test

The full code should look something like this:

```Javascript
/* Test Class */

var kuzzle;

function test(){
    kuzzle = new Kuzzle("localhost");
    subscribe();
}

function subscribe() {
    kuzzle
    .collection("mycollection", "myindex")
    .subscribe(filter, function(error, notification) {
        //called each time a message is received
        if(error) handleError(error);
        else doSomething(notification);
    })
    .onDone(function(error, room) {
        /* we are now subscribed to the room */
        publish();
    });
}

function publish(){
    kuzzle
    .collection("mycollection", "myindex")
    .publishMessage({message: "hello world"});
}

```

Create a test file using your favorite test framework. For a working example of this code refer to `javascript` folder in our snippet test [github repository](https://github.com/kuzzleio/kuzzle.io-snippet-tests). You can run the test using Visual Studio Code (using the launch.json configuration provided in the repository) or simply by executing the following command: 

```bash
    jasmine JASMINE_CONFIG_PATH=spec/runner/run_realtimePubSub.json
```

The test script requires `jasmine`. If you don't already have it, you can install it with the following command:

```bash
    npm install -g jasmine
```
