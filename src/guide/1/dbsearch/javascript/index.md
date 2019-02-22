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
    npm init
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

[snippet=create]

## Search for Document

Now that the documents are created and stored in Kuzzle, let's perform a search that will return the documents that match our query in the result.

[snippet=search]

Your index.js file should now look like this:

[snippet=final]

Here we are, we have a simple bit of code that connects to Kuzzle, creates some documents and then print the number of documents that match a simple search request on the terrain property.

To run it, just use node :

```bash
    node index.js
```

By running this code, the console hsould output the following message:
```bash
    There is 1 document that match.
```
