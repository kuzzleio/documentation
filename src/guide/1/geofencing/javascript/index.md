---
layout: full.html.hbs
algolia: true
title: Javascript
---

## Geofencing with Javascript


For this example we will use Node.js. You will need to install Node.js and NPM.

Let's create a new project folder called `geofence`:


```bash
    mkdir geofence
```

Now in you `geofence` folder initialize the Node.js App:


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

Now install the Kuzzle Javascript SDK by running `npm install` in your `geofence` folder:

```bash
    npm install
```

You should now see the `kuzzle-sdk` folder under `node_modules`.

Now the project configuration is complete, we can create an `index.js` file in the `geofence` folder to program our test.

```bash
    touch index.js
```

## Connect to Kuzzle

The first thing we need to do is connect to Kuzzle. To do this write the following code:

```Javascript
var kuzzle  = new Kuzzle("localhost");
```

Here we assume you have installed Kuzzle on your localhost, if this is not the case replace the `localhost` with the ip or name of the Kuzzle server.

## Create a Geographical Boundary

Now that we have established a connection to Kuzzle, we will perform a subscription request that tells Kuzzle that the App wants to be notified anytime a user leaves a geographical boundary.

We define the geographical boundary as follows:

```Javascript
var bigBen = {
    lat: 51.510357,
    lon: -0.116773
};
var filter = {
    geoDistance: {
        location: bigBen,
        distance: '2km'
    }
};

```

This defines a circular boundary centered around [Big Ben](https://www.google.com/maps/place/Big+Ben/@51.510357,-0.116773,15z/data=!4m12!1m6!3m5!1s0x0:0xb78f2474b9a45aa9!2sBig+Ben!8m2!3d51.5007292!4d-0.116773!3m4!1s0x0:0xb78f2474b9a45aa9!8m2!3d51.5007292!4d-0.1246254) with a radius of 2km. For more information about the `geoDistance` filter click [here](http://docs.kuzzle.io/kuzzle-dsl/terms/geo-distance/). 

Note that we use the field name `location` to store the geopoint we are centered around. This means that Kuzzle will monitor the field named `location` for position changes, and so any user location document sent to Kuzzle must also contain this field.

Now the App must request a subscription to the geographical boundary defined in our JSONObject. To ensure that the App only receives a message when the `location` changes from inside the boundary to outside the boundary, we need to set the subscription scope to `out`, for more scope options click [here](http://docs.kuzzle.io/sdk-reference/collection/subscribe/).

Let's create a *subscribe* method and add the following code to it:

```Javascript
kuzzle
.collection("mycollection", "myindex")
.subscribe(filter, {scope: "out"}, function(error, result) {
        // triggered each time the user leaves the circular area around Big Ben
})
```

Since we are also going to publish the user's location from the same App, we will want to know when the subscription is successful so that we can then publish the user's location. We can do this by using the `.onDone()` method on our subscription request:

```Javascript
kuzzle
.collection("mycollection", "myindex")
.subscribe(filter, {scope: "out"}, function(error, result) {
    // triggered each time the user leaves the circular area around Big Ben
    if(error) {
        handleError(error);
    }
    else {
        console.log('User has left Big Ben');
        doSomething(result);
    }
})
.onDone(function (err, roomObject) {
    publish();
});
```

We have now programmed the subscription side of the test.

## Place the User Inside the Geographical Boundary

Now let's move on to the publish side of the test. Here we will create a document that represents the user's location, placed inside the circular boundary around Big Ben.

We will program a *publish* method that creates a document that contains three fields: `firstName`, `lastName` and `location`.

Let's start by creating the user *Ada Lovelace* located at Big Ben. Create the Document object as follows:

```Javascript
var bigBen = {
    lat: 51.510357,
    lon: -0.116773
};
var currentLocation = {
    firstName: 'Ada',
    lastName: 'Lovelace',
    location: bigBen
};

```

Now we create this document in Kuzzle.

```Javascript
/* ... */
return kuzzle
        .collection("mycollection", "myindex")
        .createDocumentPromise('326c8f08-63b0-429f-8917-b782d30930e9', currentLocation, {ifExist:"replace"})
        .then(function(result){
            /* Document Created */
        });
  
```
 
Notice that we have included a document id, this is so that we can easily reference the document later on. We can also leave the id empty and Kuzzle will generate one automatically.


## Place the User Outside the Geographical Boundary


If the document creation is successful we can go ahead and update it to change the user's location to somewhere outside the geographical boundary. Let's move the user to [Hyde Park](https://www.google.com/maps/place/Hyde+Park/@51.507268,-0.165730,15z/data=!4m5!3m4!1s0x0:0xd1af6c4f49b4bd0c!8m2!3d51.507268!4d-0.165730). Since this is an update we need to do it after the first location document is created.


```Java
/* ... */
return kuzzle
    .collection("mycollection", "myindex")
    .createDocumentPromise('326c8f08-63b0-429f-8917-b782d30930e9', currentLocation, {ifExist:"replace"})
    .then(function(result){
        var hydePark = {
            lat: 51.507268,
            lon: -0.165730
        };
        var newLocation =  {location: hydePark};

        //Update the user's location: now they are outside the circular area -> This will trigger the notification
        return kuzzle
                .collection("mycollection", "myindex")
                .updateDocument('326c8f08-63b0-429f-8917-b782d30930e9', newLocation);
    });
```

When the document update request is sent to Kuzzle, it will detect the change in location and send a message to the subscriber, which in this case is our App. 

## Run the Test

The full code should look something like this:

```Javascript
/* Test Class */

//Connect to Kuzzle
var kuzzle;

function test(){
    kuzzle  = new Kuzzle("localhost");
    subscribe();
}

function publish(){

    //Create the user's location: they are inside the circular area
    var bigBen = {
        lat: 51.510357,
        lon: -0.116773
    };
    var currentLocation = {
        firstName: 'Ada',
        lastName: 'Lovelace',
        location: bigBen
    };

    return kuzzle
            .collection("mycollection", "myindex")
            .createDocumentPromise('326c8f08-63b0-429f-8917-b782d30930e9', currentLocation, {ifExist:"replace"})
            .then(function(result){
                var hydePark = {
                    lat: 51.507268,
                    lon: -0.165730
                };

                var newLocation =  {location: hydePark};

                //Update the user's location: now they are outside the circular area -> This will trigger the notification
                return kuzzle
                    .collection("mycollection", "myindex")
                    .updateDocument('326c8f08-63b0-429f-8917-b782d30930e9', newLocation);
            });
}

function subscribe(){

    //Create a filter that defines the circular area around Big Ben
    var bigBen = {
        lat: 51.510357,
        lon: -0.116773
    };
    var filter = {
        geoDistance: {
        location: bigBen,
        distance: '2km'
        }
    };

    //Create a subscription that triggers a notification when a user the circular area
    kuzzle
        .collection("mycollection", "myindex")
        .subscribe(filter, {scope: "out"}, function(error, result) {
            // triggered each time the user leaves the circular area around Big Ben
            if(error) {
                handleError(error);
            }
            else {
                console.log('User has left Big Ben');
                doSomething(result);
            }
        })
        .onDone(function (err, roomObject) {
        
            publish();

        });
}

```

Create a test file using your favorite test framework. For a working example of this code refer to `javascript` folder in our snippet test [github repository](https://github.com/kuzzleio/kuzzle.io-snippet-tests). You can run the test using Visual Studio Code (using the launch.json configuration provided in the repository) or simply by executing the following command: 

```bash
    jasmine JASMINE_CONFIG_PATH=spec/runner/run_geofence.json
```

The test script requires `jasmine`. If you don't already have it, you can install it with the following command:

```bash
    npm install -g jasmine
```

