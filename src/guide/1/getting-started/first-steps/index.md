---
layout: full.html.hbs
title: First Steps
description: learn kuzzle in a few steps
order: 200
---

# First Steps with Kuzzle

In this tutorial you will learn how to install, run and use **Kuzzle** in just a few steps. We will walk you through creating an app that can **store** documents in Kuzzle and generate a **notification** for each new document.

## Running Kuzzle

This section learns you how to quickly get Kuzzle up and running using our installation script.

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


#### Helper scripts for systemd

If you want to run Kuzzle automatically at startup there are a few scripts that help you do this with systemd.

If you want to run Kuzzle automatically at startup there are a few scripts in `$PWD/kuzzle/script/` that help you do this with systemd:

* Run the `add-kuzzle-boot-systemd.sh` as root to add a service inside /etc/systemd/system that will start Kuzzle on boot.
* Run the `remove-kuzzle-boot-systemd.sh` as root to remove the service so that Kuzzle won't start on boot.

#### What now?

Now that Kuzzle is up and running, you can start playing around with it:

* install <a href="{{ site_base_path }}guide/1/essentials/installing-console">Kuzzle Admin Console</a>, a handy way to manage data and security in your Kuzzle installation
* install a <a href="{{ site_base_path }}sdk-reference/">Kuzzle SDK</a> to power-up one of your projects
* explore the <a href="{{ site_base_path }}api/1">Kuzzle API</a> documentation
* install Kuzzle <a href="{{ site_base_path }}guide/1/essentials/installing-kuzzle/#manual-installation">without Docker</a>

## Fun with SDKs

It's time to play with the [Kuzzle SDK]({{ site_base_path }}sdk-reference). In this section, we will store a document and subscribe to notifications in Kuzzle using the Javascript SDK.

Before proceeding, please make sure your system has these programs installed:

* **Node.js** version 6 or higher (<a href="https://nodejs.org/en/download/">instructions here</a>)
* Kuzzle

### Prepare your environment

Create your playground directory and install the [Javascript SDK]({{ site_base_path }}sdk-reference) from the command line using npm:

```bash
mkdir "kuzzle-playground"
cd "kuzzle-playground"
npm install kuzzle-sdk@beta
```

<div class="alert alert-info">
If you are performing a clean install you might see some `UNMET PEER DEPENDENCY` warnings, these are safe to ignore as they refer to optional dependencies.
</div>

Then, create an `init.js` file and start by loading the Kuzzle Javascript SDK.
Next we will instantiate a client that will automatically connect to Kuzzle via websockets. Replace 'kuzzle' with the corresponding server name or ip address, or localhost :

[snippet=load-sdk]
Finally, we will add the code that will access Kuzzle to create a new index 'playground' and a new collection 'mycollection' that we will use to store data later on.

[snippet=init-sample]

Your `first-step.js` file should now look like this:

  [snippet=init]

This code does the following:
* loads the `Kuzzle SDK` from its NPM package
* creates an instance of the SDK and connects it to Kuzzle running on `localhost` (and selects the `playground` as default index),
* creates the `playground` index,
* creates the `mycollection` collection (within the `playground` index),
* disconnects from Kuzzle after the collection is created or if an error occurs.

Run your file in Node.js

```bash
node first-step.js
```

Your console should output the following message:

```bash
playground/mycollection ready
```

<div class="alert alert-success">
Congratulations! You are now ready to say Hello to the World!
</div>

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

### Create your first "Hello World" document

Create a `create.js` file with the following code:

  [snippet=create]

This code does the following:
* creates a new document containing the message "Hello, World" in `mycollection` within the `playground` index,
* logs a success message to the console if everything went fine,
* logs an error message if any of the previous actions failed,
* disconnects from Kuzzle after the document is created or if an error occurs.

Run your file in Node.js

```bash
node create.js
```

Your console should output the following message:

```bash
document created
```

<div class="alert alert-success">
You have now successfully stored your first document into Kuzzle. Click <a href="{{ site_base_path }}guide/1/essentials/installing-console">here</a> to see how you can use the
  <strong>Kuzzle Admin Console</strong> to browse your collection and confirm that your document was saved.
</div>

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

_You can find more resources about Kuzzle SDK in the [SDK Reference]({{ site_base_path }}sdk-reference)._

### Subscribe to data changes (pub/sub)

Kuzzle provides pub/sub features that can be used to trigger real-time notifications based on the state of your data (for a deep-dive on notifications check out the **Room** class definition in the <a href="{{ site_base_path }}sdk-reference">SDK Reference</a>).

Let's get started. Complete your `create.js` file :

  [snippet=subscribe]

Run your file in Node.js

```bash
node create.js
```

This will create a new document in Kuzzle which will trigger a [notification]({{ site_base_path }}guide/1/essentials/real-time) :

```bash
subscribe ok
document created
message received from kuzzle: Hello, World!
```

<div class="alert alert-success">
Congratulations! You have just choreographed your first pub/sub pattern!
</div>

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

## Where do we go from here?

Now that you're more familiar with Kuzzle, dive even deeper to learn how to leverage its full capabilities:

* take a look at the <a href="{{ site_base_path }}sdk-reference">SDK Reference</a>
* learn how to use <a href="{{ site_base_path }}koncorde/1">Koncorde</a> to create incredibly fine-grained and blazing-fast subscriptions
* follow our guide to learn how to implement <a href="{{ site_base_path }}guide/1/essentials/user-authentication/#local-strategy">basic authentication</a>
* follow our guide to learn how to implement <a href="{{ site_base_path }}guide/1/essentials/security/">manage users and setup fine-grained access control</a>
