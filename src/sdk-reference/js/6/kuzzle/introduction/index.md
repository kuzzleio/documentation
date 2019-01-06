---
layout: sdk.html.hbs
title: Introduction
description: Kuzzle object
order: 0
---

# Kuzzle

The Kuzzle class is the main class of the SDK.  
Once instantiated, it represents a connection to your Kuzzle server.

It gives access to the different functionalities of the SDKs:
 - access to the available controllers
 - [SDK events]({{ site_base_path }}sdk-reference/cpp/1/events) handling
 - activation of resilience to connection loss
 - offline queue management

## Network protocol

Each instance of the class communicates with the Kuzzle server through a class representing a network protocol implementation.  

The following protocols are available in the SDK JS 6:
  - [WebSocket]({{ site_base_path }}sdk-reference/js/6/websocket)
  - [Http]({{ site_base_path }}sdk-reference/js/6/http)
  - [SocketIO]({{ site_base_path }}sdk-reference/js/6/socketio)

## Volatile data

Volatile data are additional data that will be transmitted to Kuzzle in each request.  

This data is used in real-time notifications for [user join/leave notifications]({{site_base_path}}api/1/essentials/volatile-data/) to provide additional informations about the client who sent the request.

If additional data is provided within a specific request, the volatile data will be merged with the request specific data taking priority over the global ones.

## Properties

Available properties.

| Property name        | Type     | Description          | Writable? |
| -------------------- | -------- | --------------------------------------- | :-------: |
| `autoQueue`          | <pre>boolean</pre> | Automatically queue all requests during offline mode    |    Yes    |
| `autoReplay`         | <pre>boolean</pre> | Automatically replay queued requests on a `reconnected` event        |    Yes    |
| `autoResubscribe`    | <pre>boolean</pre> | Automatically renew all subscriptions on a `reconnected` event       |    Yes    |
| `jwt`                | <pre>string</pre> | Token used in requests for authentication        |    Yes    |
| `offlineQueue`       | <pre>object[]</pre> | Contains the queued requests during offline mode   |    No     |
| `offlineQueueLoader` | <pre>function</pre> | Called before dequeuing requests after exiting offline mode,</br> to add items at the beginning of the offline queue  |    Yes    |
| `protocol`       | <pre>Protocol</pre> | Protocol used by the SDK   |    No     |
| `queueFilter`        | <pre>function</pre> | Called during offline mode. </br>Takes a request object as arguments and returns a boolean, indicating if a request can be queued |    Yes    |
| `queueMaxSize`       | <pre>number</pre>  | Number of maximum requests kept during offline mode|    Yes    |
| `queueTTL`           | <pre>number</pre>  | Time a queued request is kept during offline mode, in milliseconds      |    Yes    |
| `replayInterval`     | <pre>number</pre>  | Delay between each replayed requests               |    Yes    |
| `volatile`           | <pre>object</pre> | Common volatile data, will be sent to all future requests       |    Yes    |

**Notes:**

- multiple methods allow passing specific `volatile` data. These `volatile` data will be merged with the global Kuzzle `volatile` object when sending the request, with the request specific `volatile` taking priority over the global ones.
- the `queueFilter` property is a function taking an object as an argument. This object is the request sent to Kuzzle, following the [Kuzzle API]({{ site_base_path }}api/1/essentials/query-syntax) format
- if `queueTTL` is set to `0`, requests are kept indefinitely
- The offline buffer acts like a first-in first-out (FIFO) queue, meaning that if the `queueMaxSize` limit is reached, older requests are discarded to make room for new requests
- if `queueMaxSize` is set to `0`, an unlimited number of requests is kept until the buffer is flushed
- the `offlineQueueLoader` must be set with a function, taking no argument, and returning an array of objects containing a `query` member with a Kuzzle query to be replayed, and an optional `cb` member with the corresponding callback to invoke with the query result
