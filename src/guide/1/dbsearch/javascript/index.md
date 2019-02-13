---
layout: full.html.hbs
title: Javascript
---


## Database Search with Javascript

For this example we will use Node.js. You will need to install Node.js and NPM.

Let's create a new project folder called `databaseSearch`:


```bash
    mkdir databaseSearch
```

Now install kuzzle JS SDK v6:


```bash
    npm install kuzzle-sdk@beta
```

Now the project configuration is complete, we can create an `index.js` file in the `databaseSearch` folder to program our test.

```bash
    touch index.js
```
## Instanciate Kuzzle

The first thing we need to do is instanciate a Kuzzle object. To do this write the following code:

```Javascript
const {
    Kuzzle,
    WebSocket
} = require('kuzzle-sdk');

const kuzzle = new Kuzzle(
    new WebSocket('localhost')
);
```

Here we assume you have installed Kuzzle on your localhost, if this is not the case replace the `localhost` with the ip or name of the Kuzzle server.

All the following snippets must be write in a try catch like this :

```Javascript
const run = async () => {
    try {
        /* WRITE HERE THE FOLLOWING SNIPPETS */
    } catch (error) {
        console.log(error.message);
    } finally {
        kuzzle.disconnect();
    }
}
```

## Connect to Kuzzle

We need now to connect to Kuzzle :

```Javascript
        await kuzzle.connect();
```

## Create an Index and a Collection

Now that we have established a connection to Kuzzle, we will create new Index and a new Collection.
To be sure of the result of our search request, if an index already exists we will delete it: 

```Javascript 
if (await kuzzle.index.exists('galaxies')) {
    await kuzzle.index.delete('galaxies');
}
        
await kuzzle.index.create('galaxies');
await kuzzle.collection.create('galaxies', 'planets');
```

## Create a Document

Now that we have configured our database, we will create two document in our `planets` collection. 
To do this, we will use the create method, wich take 3 parameters, the index name, the collection name, and an object with the needed properties:

```Javascript
await kuzzle.document.create(
    'galaxies',
    'planets',
    { terrain: 'mountain' },
);
await kuzzle.document.create(
    'galaxies',
    'planets',
    { terrain: 'other' },
);
        
```

To be sure that our search request will find our documents, we need to call the refresh method :

```Javascript
        await kuzzle.index.refresh('galaxies');
```


## Search for Document

Now that the documents are created and stored in Kuzzle, let's perform a search that will return the documents that match our query in the result.

The search method take also the index and collection names, and an object wich contain our query as 3rd parameter.

```Javascript
const results = await kuzzle.document.search(
            'galaxies',
            'planets',
            {
                query: {
                    match: {
                        terrain: 'mountain'
                    }
                }
            }
        );
console.log(results.hits.length);
```

There you have it, a simple bit of code that connects to Kuzzle, creates two documents and then print the number of documents that match the mountain terrain property.

## Run the Test

The full code should look something like this:

```Javascript
//Require and instanciate kuzzle
const {
    Kuzzle,
    WebSocket
} = require('kuzzle-sdk');

const kuzzle = new Kuzzle(
    new WebSocket('localhost')
);

const run = async () => {
    try {
        //Wait for etablished connection to Kuzzle
        await kuzzle.connect();

        //Delete the galaxies index if exists
        if (await kuzzle.index.exists('galaxies')) {
            await kuzzle.index.delete('galaxies');
        }
        
        //Create galaxies index, planets collection and 2 documents 
        //with different terrain property
        await kuzzle.index.create('galaxies');
        await kuzzle.collection.create('galaxies', 'planets');
        await kuzzle.document.create(
            'galaxies',
            'planets',
            { terrain: 'mountain' },
        );
        await kuzzle.document.create(
            'galaxies',
            'planets',
            { terrain: 'other' },
        );
        
        //Wait for index refreshed
        await kuzzle.index.refresh('galaxies');

        //Search for documents with mountain as terrain property
        const results = await kuzzle.document.search(
            'galaxies',
            'planets',
            {
                query: {
                    match: {
                        terrain: 'mountain'
                    }
                }
            }
        );

        //If everything's okay, 1 will be printed on stdout
        console.log(results.hits.length);
    } catch (error) {
        console.error(error.message);
    } finally {
        //Disconnecting kuzzle
        kuzzle.disconnect();
    }
};

run();

```
To run it, just use node :

```bash
    node index.js
```
