---
layout: full.html.hbs
algolia: true
title: Node.js
description: Getting started with Kuzzle and Node.js
---

# Getting Started with Kuzzle and Node.js

This tutorial explains you how to use **Kuzzle** with **Node.js** and the **Javascript SDK**.  
It will walk you through creating scripts that can **store** documents in Kuzzle and subscribe to **notifications** about document creations.

You are going to write an application that **stores** documents in Kuzzle Server and subscribe to **real time notifications** for each created document.

To follow this tutorial, you must have a Kuzzle Server up and running. Follow these instructions if this is not already the case: [Running Kuzzle]({{ site_base_path }}guide/1/getting-started/running-kuzzle).

## Fun with the SDK

It's time to play with the [Kuzzle Javascript SDK]({{ site_base_path }}sdk-reference/js/6). This section, explains you how to store a document and subscribe to notifications in Kuzzle using the Javascript SDK.

Before proceeding, please make sure your system has **Node.js** version 8 or higher (<a href="https://nodejs.org/en/download/">instructions here</a>) installed.

## Prepare your environment

Create your playground directory and install the [Javascript SDK]({{ site_base_path }}sdk-reference/js/6) from the command line using npm:

```sh
mkdir "kuzzle-playground"
cd "kuzzle-playground"
npm install git://github.com/kuzzleio/sdk-javascript.git#6-beta
```

<div class="alert alert-info">
If you are performing a clean install you might see some `UNMET PEER DEPENDENCY` warnings, these are safe to ignore as they refer to optional dependencies.
</div>

Then, create an `init.js` file and start by adding the code below.  
This loads the SDK and connects it to a Kuzzle instance using WebSocket.  

[snippet=load-sdk]

<div class="alert alert-info">
Replace 'kuzzle' which is the Kuzzle backend hostname with 'localhost' or the hostname where your Kuzzle backend is running.
</div>

Next, add a listener to be notified in case of a connection error:

```javascript
kuzzle.on('networkError', error => {
  console.error('Network Error: ', error);
});
```

Then, connect the client to your Kuzzle server with the `connect()` method, afterwards you have to add the code that will access Kuzzle to create a new index 'nyc-open-data' and a new collection 'yellow-taxi' that you will use to store data later on.  
These methods will be wrapped in an `async` function to use the `await` construct.

[snippet=prepare-db]

Your `init.js` file should now look like this:

[snippet=init]

This code does the following:
* loads the `Kuzzle SDK` from its NPM package
* creates an instance of the SDK
* connects it to Kuzzle running on `kuzzle` (change the hostname if needed) using WebSocket
* creates the `nyc-open-data` index
* creates the `yellow-taxi` collection (within the `nyc-open-data` index),
* disconnects from Kuzzle after the collection is created or if an error occurs

Run the code with Node.js:

```bash
node init.js
```

The console should output the following message:

```bash
nyc-open-data/yellow-taxi ready!
```

<div class="alert alert-success">
Congratulations! You are now ready to say Hello to the World!
</div>

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

## Create your first "Hello World" document

Create a `create.js` file with the following code:

[snippet=create]

This code does the following:
* creates a new document in the `yellow-taxi` collection, within the `nyc-open-data` index
* logs a success message to the console if everything went fine
* logs an error message if any of the previous actions fails
* disconnects from Kuzzle after the document is created or if an error occurs

Run the code with Node.js:

```bash
node create.js
```

<div class="alert alert-success">
You have now successfully stored your first document into Kuzzle. Click <a href="{{ site_base_path }}guide/1/essentials/installing-console">here</a> to see how you can use the
   <a href="http://console.kuzzle.io" target="_blank"><strong>Kuzzle Admin Console</strong></a> to browse your collection and confirm that your document was saved.
</div>

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

_You can find more resources about Kuzzle Javascript SDK in the [SDK Reference]({{ site_base_path }}sdk-reference/js/6)._

## Subscribe to realtime document notifications (pub/sub)

Kuzzle provides pub/sub features that can be used to trigger real-time notifications based on the state of your data (for a deep-dive on notifications check out the <a href="{{ site_base_path }}sdk-reference/js/6/essentials/realtime-notifications/">realtime notifications</a> documentation).

Let's get started. Create a `subscribe.js` file with the following code:

[snippet=subscribe]

Run the code with Node.js:

```bash
node subscribe.js
```

The `subscribe.js` program is now running endlessly, waiting for notifications about documents matching its filters, specifically documents that have a `license` field equal to `'B'`.

Now in another terminal, launch the `create.js` file from the previous section.

```bash
node create.js
```

This creates a new document in Kuzzle which, in turn, triggers a [document notification]({{ site_base_path }}api/1/essentials/notifications/#documents-changes-messages-default) sent to the `subscribe.js` program.  
Check the `subscribe.js` terminal: a new message is printed everytime a document is created using the `create.js` code.

```bash
New driver Sirkis with id AWccRe3-DfukVhSzMdUo has B license.
```

<div class="alert alert-success">
Congratulations! You have just set up your first pub/sub communication!
</div>

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

## Where do we go from here?

Now that you're more familiar with Kuzzle, dive even deeper to learn how to leverage its full capabilities:

* discover what this SDK has to offer by browsing other sections of this documentation
* learn how to use <a href="{{ site_base_path }}koncorde/1">Koncorde</a> to create incredibly fine-grained and blazing-fast subscriptions
* follow our guide to learn how to perform a <a href="{{ site_base_path }}guide/1/essentials/user-authentication/#local-strategy">basic authentication</a>
* follow our guide to learn how to <a href="{{ site_base_path }}guide/1/essentials/security/">manage users, and how to set up fine-grained access control</a>
