---
layout: sdk.html.hbs
title: Getting started
description: Getting started
order: 100
separator: essentials
---

# Getting Started

In this tutorial you will learn how to install and use the **Kuzzle C++ SDK**.

You will learn :

* how to **connect** to your Kuzzle Server instance
* how to create an index and a collection
* how to **store** documents
* how to to **subcribe** to real-time notifications

<div class="alert alert-success">
Before proceeding, please make sure your system meets the following requirements :

* A C++ compiler that supports C++ 11 sush as: **gcc** version 4.5 or higher
* A running instance of Kuzzle Server (<a href="guide/1/essentials/installing-kuzzle/">Kuzzle installation guide</a>)

</div>

## Installation

Go to https://dl.kuzzle.io/sdk/cpp/1-dev and download the archive corresponding to your target artchitecture.

For amd64 linux :

``` bash
$ curl https://dl.kuzzle.io/sdk/cpp/1-dev/kuzzlesdk-cpp-experimental-amd64.tar.gz
```

Extract the archive :

``` bash
$ tar xf kuzzlesdk-cpp-experimental-amd64.tar.gz
```

To make building the sample application in the following step easier, we'll export the path to the **Kuzzle C++ SDK** to `KUZZLE_SDK_PATH` environnement variable :

``` sh
$ export KUZZLE_SDK_PATH=$PWD/kuzzle-cpp-sdk
```

## Building the application

To build the sample C++ application, we'll use the following `Makefile` :

``` Makefile
K_LIB_PATH = $(KUZZLE_SDK_PATH)/lib
K_INCLUDE_PATH = $(KUZZLE_SDK_PATH)/include

EXEC_NAME=my-first-kuzzle-app
SRCS = my-first-kuzzle-app.cpp

all:
    gcc -o $(EXEC_NAME) $(SRCS) -std=c++11 -lstdc++ -I$(K_INCLUDE_PATH) -L$(K_LIB_PATH) -lkuzzlesdk -lpthread -Wl,-rpath=$(K_LIB_PATH)
```

## First connection

Then create a `my-first-kuzzle-app.cpp` file and with the following source code :

[snippet=connect]

## Create data structure

Now we'll learn how to nitializes the Kuzzle Server storage by creating an index, and a collection inside it using **Kuzzle C++ SDK**

Update `my-first-kuzzle-app.cpp` with the following content :

[snippet=initstorage]

Build and execute the program with the following commands :

``` sh
$ make
gcc -o my-first-kuzzle-app my-first-kuzzle-app.cpp -std=c++11 -lstdc++ -I./kuzzle-cpp-sdk/include -L./kuzzle-cpp-sdk/lib -lkuzzlesdk -lpthread -Wl,-rpath=./kuzzle-cpp-sdk/lib
$ ./my-first-kuzzle-app
Connected to Kuzzle Server
Index 'nyc-open-data' and collection 'yellow-taxi' created

```

<!-- Congratulations, you performed a first connection to Kuzzle with a Go program.
You are now able to:
* Load the `Kuzzle Go SDK` from your `GOPATH` directory
* Instantiate a protocol (here `websocket`) and a Kuzzle SDK instance
* Connect to a Kuzzle instance running on `localhost`, with the WebSocket protocol
* Create a data index
* Create a data collection within an existing index -->

## Create your first document

Now that you successfully connected to your Kuzzle server with the Go SDK, and created an index and a collection, it's time to manipulate data.

Here is how Kuzzle storage is structured :

* indexes contain collections
* collections contain documents

We will create a document in `nyc-open-data/yellow-taxi`

Create a `create-document.cpp` file with the following source code :

[snippet=create-document]

```
gcc -o create-document-app create-document.cpp -std=c++11 -lstdc++ -I${KUZZLE_SDK_PATH}/include -L${KUZZLE_SDK_PATH}/lib -lkuzzlesdk -lpthread -Wl,-rpath=${KUZZLE_SDK_PATH}/lib
```

As you did before, run your program:

```bash
$ go run document.go
Connected!
New document added to yellow-taxi collection!
```

You can perform other actions such as [delete]({{ site_base_path }}sdk-reference/go/1/document/delete),
[replace]({{ site_base_path }}sdk-reference/go/1/document/replace) or [search]({{ site_base_path }}sdk-reference/go/1/document/search) documents. There are also other ways to interact with Kuzzle like our [Admin Console]({{ site_base_path }}guide/2/essentials/installing-console/), the [Kuzzle HTTP API]({{ site_base_path }}api/1/essentials/connecting-to-kuzzle/) or by using your [own protocol](protocols/1/essentials/getting-started/).

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
