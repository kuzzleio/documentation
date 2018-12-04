---
layout: sdk.html.hbs
algolia: true
title: Getting started
description: Getting started
order: 100
separator: essentials
---

# Getting Started

In this tutorial you will learn how to install the Kuzzle **Go SDK**.
We will walk you through creating scripts that can **store** documents in Kuzzle and subscribe to **notification** for each new document created.

<div class="alert alert-success">
Before proceeding, please make sure your system has these programs installed:

* **Go** version 1.9 or higher (you can find instructions <a href="https://golang.org/doc/install">here</a>).
* A running Kuzzle server (you can find instructions <a href="guide/1/essentials/installing-kuzzle/">here</a>).
</div>

## Installation

You can easily install our Go SDK:

```bash
$ go get github.com/kuzzleio/sdk-go
```

This will fetch the SDK and install it in your `GOPATH`.

## First connection

Initialize a new Go project as described in [Go Documentation](https://golang.org/doc/code.html#Command).
Then create a `init.go` file and start by adding the code below:

[snippet=init]

This program will initialize our Kuzzle creating an index and a collection
we'll use in the next section.
Run the program with the following command:

```bash
$ go run init.go
Connected!
Index nyc-open-data created!
Collection yellow-taxi created!
```

Congratulations, you performed a first connection to Kuzzle with a Go program.
You are now able to:
* Load the `Kuzzle Go SDK` from your `GOPATH`.
* Instantiate a protocol (here `websocket`) and a Kuzzle SDK instance.
* Connect to Kuzzle instance running on `localhost` with the `websocket` protocol.
* Create the `nyc-open-data` index.
* Create the `yellow-taxi` collection (within the `nyc-open-data` index).

## Create your first document

Now you successfully connected to your Kuzzle server with the Go SDK and have an index and a collection, it's time to manipulate data. Kuzzle uses `indexes` that contain `collections`.
Those `collections` store `documents`.
Create a `document.go` file in the playground and add this code:

[snippet=document]

As you did before, run your program:

```bash
$ go run document.go
Connected!
New document added to yellow-taxi collection!
```

You can perform other actions such as [delete]({{ site_base_path }}sdk-reference/go/1/document/delete),
[replace]({{ site_base_path }}sdk-reference/go/1/document/replace) or [search]({{ site_base_path }}sdk-reference/go/1/document/search) documents. There are also other way to manipulate data in Kuzzle
like our [Admin Console]({{ site_base_path }}guide/2/essentials/installing-console/), the [Kuzzle HTTP API]({{ site_base_path }}api/1/essentials/connecting-to-kuzzle/) or your [own protocol](protocols/1/essentials/getting-started/).

Now you know how to:
* Store data in your Kuzzle server with documents and access it.

## Subscribe to realtime document notifications (pub/sub)

Time to use realtime with Kuzzle. Create a new file `realtime.go` containing:

[snippet=realtime]

This program subscribe to `yellow-taxi` collection. For each new document filter matching, we'll receive notification.
Run your program:

```bash
$ go run realtime.go
Connected!
Successfully subscribing!
New document added to yellow-taxi collection!
Driver John born on 1995-11-27 got a B license.
```

Now, you know how to:
* Create realtime filters.
* Subscribe to notifications.

<div class="alert alert-info">
If you want to go deeper in the SDK, source code is available on <a href="https://github.com/kuzzleio/sdk-go">Github</a>.
</div>

<div class="alert alert-info">
Having trouble? Get in touch with us on <a href="https://gitter.im/kuzzleio/kuzzle">Gitter!</a> We're happy to help.
</div>

## Where do we go from here?

Now that you're more familiar with Kuzzle Go SDK, dive even deeper to learn how to leverage its full capabilities:

* take a look at the <a href="{{ site_base_path }}sdk-reference/js/6">SDK Reference</a>
* learn how to use <a href="{{ site_base_path }}koncorde/1">Koncorde</a> to create incredibly fine-grained and blazing-fast subscriptions
* follow our guide to learn how to implement <a href="{{ site_base_path }}guide/1/essentials/user-authentication/#local-strategy">basic authentication</a>
* follow our guide to learn how to implement <a href="{{ site_base_path }}guide/1/essentials/security/">manage users and setup fine-grained access control</a>
