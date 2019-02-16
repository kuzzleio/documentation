---
layout: sdk.html.hbs
title: Getting started
description: Getting started
order: 100
separator: essentials
---

# Getting Started

In this tutorial you will learn how to install the Kuzzle **Go SDK**.
This page shows examples of scripts that **store** documents in Kuzzle, and of scripts that subcribe to real-time **notifications** for each new document created.

<div class="alert alert-success">
Before proceeding, please make sure your system meets the following requirements:

* **Go** version 1.9 or higher (<a href="https://golang.org/doc/install">Go installation instructions</a>)
* A running Kuzzle server (<a href="{{site_base_path}}guide/1/essentials/installing-kuzzle/">Kuzzle installation guide</a>)
</div>

## Installation

To easily install the Go SDK:

```bash
$ go get github.com/kuzzleio/sdk-go
```

This fetches the SDK and installs it in your `GOPATH` directory.

## First connection

Initialize a new Go project as described in the [Go Documentation](https://golang.org/doc/code.html#Command).
Then create a `init.go` file and start by adding the code below:

[snippet=init]

This program initializes the Kuzzle server storage by creating a data index, and a data collection inside it
Run the program with the following command:

```bash
$ go run init.go
Connected!
Index nyc-open-data created!
Collection yellow-taxi created!
```

Congratulations, you performed a first connection to Kuzzle with a Go program.
You are now able to:
* Load the `Kuzzle Go SDK` from your `GOPATH` directory
* Instantiate a protocol (here `websocket`) and a Kuzzle SDK instance
* Connect to a Kuzzle instance running on `localhost`, with the WebSocket protocol
* Create a data index
* Create a data collection within an existing index

## Create your first document

Now that you successfully connected to your Kuzzle server with the Go SDK, and created an index and a collection, it's time to manipulate data.

Here is how Kuzzle structures its storage space:
- data indexes contain data collections
- data collections contain documents
Create a `document.go` file in the playground and add this code:

[snippet=document]

As you did before, run your program:

```bash
$ go run document.go
Connected!
New document added to yellow-taxi collection!
```

You can perform other actions such as [delete]({{ site_base_path }}sdk-reference/go/1/document/delete),
[replace]({{ site_base_path }}sdk-reference/go/1/document/replace) or [search]({{ site_base_path }}sdk-reference/go/1/document/search) documents. There are also other ways to interact with Kuzzle like our [Admin Console]({{ site_base_path }}guide/1/essentials/installing-console/), the [Kuzzle HTTP API]({{ site_base_path }}api/1/essentials/connecting-to-kuzzle/) or by using your [own protocol]({{site_base_path}}protocols/1/essentials/getting-started/).

Now you know how to:
* Store documents in a Kuzzle server, and access those

## Subscribe to realtime document notifications (pub/sub)

Time to use realtime with Kuzzle. Create a new file `realtime.go` with the following code:

[snippet=realtime]

This program subscribes to changes made to documents with a `license` field set to `B`, within the `yellow-taxi` collection. Whenever a document matching the provided filters changes, a new notification is received from Kuzzle.
Run your program:

```bash
$ go run realtime.go
Connected!
Successfully subscribing!
New document added to yellow-taxi collection!
Driver John born on 1995-11-27 got a B license.
```

Now, you know how to:
* Create realtime filters
* Subscribe to notifications

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

## Where do we go from here?

Now that you're more familiar with the Go SDK, you can dive even deeper to learn how to leverage its full capabilities:

* discover what this SDK has to offer by browsing other sections of this documentation
* learn how to use <a href="{{ site_base_path }}koncorde/1">Koncorde</a> to create incredibly fine-grained and blazing-fast subscriptions
* follow our guide to learn how to perform <a href="{{ site_base_path }}guide/1/essentials/user-authentication/#local-strategy">basic authentication</a>
* follow our guide to learn how to <a href="{{ site_base_path }}guide/1/essentials/security/">manage users and how to set up fine-grained access control</a>
