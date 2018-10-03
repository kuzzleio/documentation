---
layout: full.html.hbs
algolia: true
title: Javascript
---


## Database Search with Javascript

For this example we will use Node.js. You will need to install Node.js and NPM.

Let's create a new project folder called `databaseSearch`:


```bash
    mkdir databaseSearch
```

Now in you `databaseSearch` folder initialize the Node.js App:


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

Now install the Kuzzle Javascript SDK by running `npm install` in your `databaseSearch` folder:

```bash
    npm install
```

You should now see the `kuzzle-sdk` folder under `node_modules`.

Now the project configuration is complete, we can create an `index.js` file in the `databaseSearch` folder to program our test.

```bash
    touch index.js
```

## Connect to Kuzzle

The first thing we need to do is connect to Kuzzle. To do this write the following code:

```Javascript
var kuzzle  = new Kuzzle("localhost");
```

Here we assume you have installed Kuzzle on your localhost, if this is not the case replace the `localhost` with the ip or name of the Kuzzle server.

## Create a Document

Now that we have established a connection to Kuzzle, we will create a document in our `planets` collection. To do this, we use the Collection  `createDocumentPromise` method.

In this case we create a JSON with name `Geonosis` and terrain `mountain`:

```Javascript
var planet = {
    name: 'Geonosis',
    terrain: 'mountain'
};
    
return kuzzle
        .collection("planets", "galaxies")
        .createDocumentPromise("", planet, {refresh: "wait_for"})
        .then(function(result){
            /* TODO */
        });
});
```

To ensure that this code is executed only after the Kuzzle connection is established, we can call it after we receive the 'connected' event from the Kuzzle Javascript SDK:

```Javascript
kuzzle.on("connected",function(error){
    var planet = {
        name: 'Geonosis',
        terrain: 'mountain'
    };
        
    return kuzzle
            .collection("planets", "galaxies")
            .createDocumentPromise("", planet, {refresh: "wait_for"})
            .then(function(result){
                /* TODO */
            });
    });
});
```


## Search for Document

Now that the document is created and stored in Kuzzle, let's perform a search that will return this document in the result.

First we need to define the search criteria. Here we use the `match` term to find any document that has a `mountain` terrain. For additional terms refer to our [Elasticsearch Cookbook](http://docs.kuzzle.io/elasticsearch-cookbook) or Elasticsearch's own documentation.

We use the Collection `searchPromise` method to search for the document in Kuzzle only after the document is created:

```Javascript
kuzzle.on("connected",function(error){
    var planet = {
        name: 'Geonosis',
        terrain: 'mountain'
    };
        
    return kuzzle
            .collection("planets", "galaxies")
            .createDocumentPromise("", planet, {refresh: "wait_for"})
            .then(function(result){
                
                //Now search for the document
                return kuzzle
                    .collection("planets", "galaxies")
                    .searchPromise({query: {match: {terrain: "mountain"}}})
                    .then(function(result){
                        doSomething(result);
                    });
            });
    });
});
```

There you have it, a simple bit of code that connects to Kuzzle, creates a document and then fetches that document.

## Run the Test

The full code should look something like this:

```Javascript
/* Test Class */

//Connect to Kuzzle
var kuzzle  = new Kuzzle("localhost");


//Wait for the connection to establish
kuzzle.on("connected",function(error){

    //Create a document
    var planet = {
        name: 'Geonosis',
        terrain: 'mountain'
    };

    return kuzzle
            .collection("planets", "galaxies")
            .createDocumentPromise("", planet, {refresh: "wait_for"})
            .then(function(result){

            //Now search for the document
            return kuzzle
                    .collection("planets", "galaxies")
                    .searchPromise({query: {match: {terrain: "mountain"}}})
                    .then(function(result){
                        doSomething(result);
                    });
    });
});

```

Create a test file using your favorite test framework. For a working example of this code refer to `javascript` folder in our snippet test [github repository](https://github.com/kuzzleio/kuzzle.io-snippet-tests). You can run the test using Visual Studio Code (using the launch.json configuration provided in the repository) or simply by executing the following command: 

```bash
    jasmine JASMINE_CONFIG_PATH=spec/runner/run_databaseSearch.json
```

The test script requires `jasmine`. If you don't already have it, you can install it with the following command:

```bash
    npm install -g jasmine
```
 
