---
layout: sdk.html.hbs
title: Offline Tools
description: Tools to handle the loss of connection to the Kuzzle server
order: 400
---

# Offline tools

The Kuzzle SDK provides a set of properties that help your application to be resilient to the loss of network connection
during its lifespan. 

## Kuzzle SDK properties

These properties can be set in the `options` object when [instantiating a new SDK]({{ site_base_path }}sdk-reference/js/6/kuzzle/constructor/#arguments).  

Some of them are also [writable properties]({{ site_base_path }}sdk-reference/js/6/kuzzle/introduction/#properties) available after SDK instantiation.

### offlineQueue

A read-only `Array` containing the requests queued while the SDK is in the `offline` state (it behaves like a FIFO queue).

### queueMaxSize

A writable `number` defining the maximun size of the `offlineQueue`.

Default value: `500`

### queueTTL

A writable `number` defining the time in milliseconds a queued request is kept in the `offlineQueue`.

Default value: `120000`

### autoQueue

A writable `boolean` telling the SDK whether to automatically queue requests during the `offline` state or not.

Default value: `false`

### autoReplay

A writable `boolean` telling the SDK whether to automatically send or not the requests in the `offlineQueue` on a
`reconnected` event.

Default value: `false`

### autoReconnect

A writable `boolean` telling the SDK whether to automatically reconnect or not to Kuzzle after a connection loss.

Default value: *Depends on the Protocol*

### reconnectionDelay 	

A read-only `number` specifying the time in milliseconds between different reconnection attempts.

Default value: *Depends on the Protocol*

### autoResubscribe

A writable `boolean` telling the SDK whether to automatically renew or not all subscriptions on a reconnected event.

Default value: `true`

### queueFilter

A writable `Function` called by the SDK each time a `Request` need to be queued. The `Request` is passed as the only argument
to the function and is queued only if the function returns `true`. Use it to define which requests are allowed to be queued.

### offlineQueueLoader

A writable `Function` called by the SDK before playing the requests in the `offlineQueue`. This function takes no arguments
and returns an array of `Request` that are added on top of the `offlineQueue`. Use it to inject new requests to be played
before the queue.

## Kuzzle SDK methods

### [startQueuing()]({{ site_base_path }}sdk-reference/js/6/kuzzle/start-queuing/)

Starts queuing requests when in `offline` state. Request will be put in the `offlineQueue` instead of being discarded, until `stopQueuing` is called.
Works only in `offline` state when the `autoQueue` option is set to `false`. Call `playQueue` to send to Kuzzle the
requests in the queue, once the SDK state passes to `online`. Call `flushQueue` to empty the queue without sending the requests.

### [stopQueuing()]({{ site_base_path }}sdk-reference/js/6/kuzzle/stop-queuing)

Stop queuing the requests. Requests will no more be put in the `offlineQueue`, they will be discarded.
Works only in the `offline` state, and if the `autoQueue` option is set to `false`.

### [playQueue()]({{ site_base_path }}sdk-reference/js/6/kuzzle/play-queue)

Sends to Kuzzle all the requests in the `offlineQueue`. Works only if the SDK is not in a `offline` state, and if the 
`autoReplay` option is set to false.

### [flushQueue()]({{ site_base_path }}sdk-reference/js/6/kuzzle/flush-queue)

Empties the `offlineQueue` without sending the requests to Kuzzle.
