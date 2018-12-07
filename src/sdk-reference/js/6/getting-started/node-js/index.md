---
layout: full.html.hbs
algolia: true
title: Node.js
description: Getting started with Kuzzle and Node.js
---

# Getting Started with Kuzzle and Node.js

In this tutorial you will learn how to install, run and use **Kuzzle** with **Node.js** and the **Javascript SDK**.
We will walk you through creating scripts that can **store** documents in Kuzzle and subscribe to **notifications** about document creations.

## Running Kuzzle

In this section we'll learn how to quickly get Kuzzle up and running using our installation script.

Open a terminal and run the following command:

```bash
bash -c "$(curl https://get.kuzzle.io/)"
```

This command downloads and executes the installation script. The script checks the system for a set of prerequisites and installs missing ones, such as [Docker](https://www.docker.com/). When the installation is complete, it will automatically run Kuzzle.

<div class="alert alert-info">
There are also more <a href="{{ site_base_path }}guide/1/essentials/installing-kuzzle/">alternative ways</a> to install Kuzzle.
</div>

This command downloads, installs and runs Kuzzle.

Use the `--no-run` option to prevent the script from running Kuzzle.

Once the installation process is complete, you will see the following message:

```bash
# Kuzzle is now running
```

Your Kuzzle is now running! To test it, you can explore the main HTTP API by clicking this <a href="http://localhost:7512?pretty">link</a> or by using cURL on the command line:

```bash
curl "http://localhost:7512/?pretty"
```

If everything is working you should see a JSON document that contains a list of API endpoints.

<div class="alert alert-success">
Congratulations! You have completed the Kuzzle installation, it will now accept requests on <code>localhost:7512</code>:
<ul>
  <li>via <strong>HTTP</strong></li>
  <li>via <strong>Websocket</strong></li>
</ul>
</div>

<div class="alert alert-info">
Having trouble?
<ul>
  <li>Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.</li>
  <li>Try one of <a href="{{ site_base_path }}guide/1/essentials/installing-kuzzle/">these</a> alternative installation methods.</li>
</ul>
</div>

## Fun with the SDK

It's time to play with the [Kuzzle Javscript SDK]({{ site_base_path }}sdk-reference/js/6). In this section, we will store a document and subscribe to notifications in Kuzzle using the Javascript SDK.

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
This will load the Kuzzle Javascript SDK and then instantiate a client that will connect to Kuzzle via websockets.  

[snippet=load-sdk]

<div class="alert alert-info">
You need to replace 'kuzzle' which is the Kuzzle backend hostname with 'localhost' or the hostname where your Kuzzle backend is running.
</div>

Next we add a listener to be notified in case of connection error:

```javascript
kuzzle.on('networkError', error => {
  console.error('Network Error: ', error);
});
```

Then we have to connect the client to your Kuzzle server with the `connect()` method.  
Then, we will add the code that will access Kuzzle to create a new index 'nyc-open-data' and a new collection 'yellow-taxi' that we will use to store data later on.  
These methods will be wrapped in an `async` function to use the `await` construct.

[snippet=prepare-db]

Your `init.js` file should now look like this:

[snippet=init]

This code does the following:
* loads the `Kuzzle SDK` from its NPM package
* creates an instance of the SDK
* connects it to Kuzzle running on `kuzzle` (change your hostname if you need) with the `websocket` protocol
* creates the `nyc-open-data` index
* creates the `yellow-taxi` collection (within the `nyc-open-data` index),
* disconnects from Kuzzle after the collection is created or if an error occurs

Run your file with Node.js

```bash
node init.js
```

Your console should output the following message:

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

Create a `create.js` file with following code:

[snippet=create]

This code does the following:
* creates a new document in `nyc-open-data` within the `yellow-taxi` index
* logs a success message to the console if everything went fine
* logs an error message if any of the previous actions failed
* disconnects from Kuzzle after the document is created or if an error occurs

Run your file with Node.js

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

Let's get started. Create a `subscribe.js` file with following code:

[snippet=subscribe]

Run your file in Node.js

```bash
node subscribe.js
```

Your `subscribe.js` app is now running and monitoring any documents that match the filter, specifically documents that have a `license` field that is equals to `'B'`.

Now in another terminal, launch the `create.js` file that we created in the previous section.

```bash
node create.js
```

This will create a new document in Kuzzle which will trigger a [document notification]({{ site_base_path }}api/1/essentials/notifications/#documents-changes-messages-default) in the `subscribe.js` app. Check the `subscribe.js` terminal to make sure a new log appears every time a document is created using the `create.js` app:

```bash
New driver Sirkis with id AWccRe3-DfukVhSzMdUo has B license.
```

<div class="alert alert-success">
Congratulations! You have just choreographed your first pub/sub pattern!
</div>

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

## Where do we go from here?

Now that you're more familiar with Kuzzle, dive even deeper to learn how to leverage its full capabilities:

* take a look at the <a href="{{ site_base_path }}sdk-reference/js/6">SDK Reference</a>
* learn how to use <a href="{{ site_base_path }}koncorde/1">Koncorde</a> to create incredibly fine-grained and blazing-fast subscriptions
* follow our guide to learn how to implement <a href="{{ site_base_path }}guide/1/essentials/user-authentication/#local-strategy">basic authentication</a>
* follow our guide to learn how to implement <a href="{{ site_base_path }}guide/1/essentials/security/">manage users and setup fine-grained access control</a>
