---
layout: full.html.hbs
title: Javascript
---


## Database Search with Javascript

For this example we will use Node.js. You will need to install Node.js and NPM.

Let's create a new project folder called `database-search`:


```bash
    mkdir database-search
```

Now install Kuzzle SDK JS 6:


```bash
    npm install kuzzle-sdk
```

Now the project configuration is complete, we can create an `index.js` file in the `database-search` folder to program our test.

```bash
    touch index.js
```
## Instanciate Kuzzle

The first thing we need to do is instanciate a Kuzzle object. To do this implement the following code:

[snippet=load-sdk]

## Connect to Kuzzle

We need now to connect to Kuzzle:

[snippet=connect]

## Create an Index, a Collection and some Documents

Now that we have established a connection to Kuzzle, we will create a new Index, a new Collection and two Documents.
To be sure of the result of our search request, if an index already exists we will delete it: 

[snippet=create]


## Search for Document

Now that the documents are created and stored in Kuzzle, let's perform a search that will return the documents that match our query in the result.

To be sure that our search request will find our documents, we need to call the refresh method before.

[snippet=search]

You're index.js file should now look like this:

[snippet=final]

There you have it, a simple bit of code that connects to Kuzzle, creates two documents and then print the number of documents that match the mountain terrain property.

To run it, just use node :

```bash
    node index.js
```

By running this code, the console hsould output the following message:
```bash
    There is 1 document that match.
```
