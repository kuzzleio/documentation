---
code: false
type: page
title: Getting started
description: Getting started
order: 99
---

# Getting Started

In this tutorial you will learn how to install and use the **Kuzzle C++ SDK**.

You will learn :

- how to **connect** to your Kuzzle Server instance
- how to create an index and a collection
- how to **store** documents
- how to to **subcribe** to real-time notifications

:::success
Before proceeding, please make sure your system meets the following requirements :

- A C++ compiler that supports C++ 11 sush as: **gcc** version 4.5 or higher
- A running instance of Kuzzle Server ([Kuzzle installation guide](/core/1/guides/essentials/installing-kuzzle/))
:::

:::info
Having trouble? Get in touch with us on [Gitter](https://gitter.im/kuzzleio/kuzzle)! 
:::

## Installation

Go to https://dl.kuzzle.io/sdk/cpp/1-dev and download the archive corresponding to your target artchitecture.

For amd64 linux :

```bash
$ curl https://dl.kuzzle.io/sdk/cpp/1-dev/kuzzlesdk-cpp-experimental-amd64.tar.gz
```

Extract the archive :

```bash
$ tar xf kuzzlesdk-cpp-experimental-amd64.tar.gz
```

To make building the sample application in the following step easier, we'll export the path to the **Kuzzle C++ SDK** to `KUZZLE_SDK_PATH` environnement variable :

```sh
$ export KUZZLE_SDK_PATH=$PWD/kuzzle-cpp-sdk
```

## First connection

Create a `my-first-kuzzle-app.cpp` file and with the following source code :

<<< ./snippets/connect.cpp

Build and run :

```bash
$ g++ -o my-first-kuzzle-app my-first-kuzzle-app.cpp -I${KUZZLE_SDK_PATH}/include -L${KUZZLE_SDK_PATH}/lib -lkuzzlesdk -lpthread -Wl,-rpath=${KUZZLE_SDK_PATH}/lib
$ ./my-first-kuzzle-app
Connected!
Server current timestamp: 1549298495451
```

## Create data structure

Now we'll learn how to nitializes the Kuzzle Server storage by creating an index, and a collection inside it using **Kuzzle C++ SDK**

Update `my-first-kuzzle-app.cpp` with the following content :

<<< ./snippets/init.cpp

Build and execute the program with the following commands :

```sh
$ g++ -o my-first-kuzzle-app my-first-kuzzle-app.cpp -I${KUZZLE_SDK_PATH}/include -L${KUZZLE_SDK_PATH}/lib -lkuzzlesdk -lpthread
$ LD_LIBRARY_PATH=${KUZZLE_SDK_PATH}/lib/ ./my-first-kuzzle-app
Connected to Kuzzle Server
Index nyc-open-data created!
Collection yellow-taxi created!

```

## Create your first document

Now that you successfully connected to your Kuzzle server with the Go SDK, and created an index and a collection, it's time to manipulate data.

Here is how Kuzzle storage is structured :

- indexes contain collections
- collections contain documents

We will create a document in `nyc-open-data/yellow-taxi`

Create a `create-document.cpp` file with the following source code :

<<< ./snippets/document.cpp

Build and run :

```bash
$ g++ -o create-document create-document.cpp -I${KUZZLE_SDK_PATH}/include -L${KUZZLE_SDK_PATH}/lib -lkuzzlesdk -lpthread
$ LD_LIBRARY_PATH=${KUZZLE_SDK_PATH}/lib/ ./create-document
Connected to Kuzzle Server
Document created successfuly
{"_index":"nyc-open-data","_type":"yellow-taxi","_id":"AWesW0cJYEmXIw2Bonx4","_version":1,"result":"created","_shards":{"total":2,"successful":1,"failed":0},"created":true,"_source":{"birthday":"1959-06-22","license":"B","name":"Sirkis","_kuzzle_info":{"author":"-1","createdAt":1544784922373,"updatedAt":null,"updater":null,"active":true,"deletedAt":null}},"_meta":{"author":"-1","createdAt":1544784922373,"updatedAt":null,"updater":null,"active":true,"deletedAt":null}}
```

You can perform other actions such as [delete](/sdk/go/1/controllers/document/delete/),
[replace](/sdk/go/1/controllers/document/replace/) or [search](/sdk/go/1/controllers/document/search/) documents. There are also other ways to interact with Kuzzle like our [Admin Console](/core/1/guides/essentials/admin-console/), the [Kuzzle HTTP API](/core/1/api/essentials/connecting-to-kuzzle/) or by using your [own protocol](/core/1/protocols/essentials/getting-started/).

Now you know how to:

- Store documents in a Kuzzle server, and access those

## Subscribe to realtime document notifications (pub/sub)

Time to use realtime with Kuzzle. Create a new file `realtime.cpp` with the following code:

<<< ./snippets/realtime.cpp

This program subscribes to changes made to documents with a `license` field set to `B`, within the `yellow-taxi` collection. Whenever a document matching the provided filters changes, a new notification is received from Kuzzle.
Run your program:

```bash
$ g++ -o realtime realtime.cpp -I${KUZZLE_SDK_PATH}/include -L${KUZZLE_SDK_PATH}/lib -lkuzzlesdk -lpthread
$ LD_LIBRARY_PATH=${KUZZLE_SDK_PATH}/lib/ ./realtime
Connected!
Successfully subscribing!
New document added to yellow-taxi collection!
Driver John born on 1995-11-27 got a B license.
```

Now, you know how to:

- Create realtime filters
- Subscribe to notifications

## Where do we go from here?

Now that you're more familiar with the Go SDK, you can dive even deeper to learn how to leverage its full capabilities:

- discover what this SDK has to offer by browsing other sections of this documentation
- learn how to use [Koncorde](/core/1/koncorde) to create incredibly fine-grained and blazing-fast subscriptions
- follow our guide to learn how to perform [basic authentication](/core/1/guides/essentials/user-authentication/#local-strategy)
- follow our guide to learn how to [manage users and how to set up fine-grained access control](/core/1/guides/essentials/security/)
