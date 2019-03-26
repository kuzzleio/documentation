---
layout: sdk.html.hbs
title: Offline Tools
description: Tools to handle the loss of connection to the Kuzzle server
order: 100
---

# Offline tools

The Kuzzle SDK provides a set of options and methods that help your application to be resilient to the loss of network connection
during its lifespan.   

## Constructor options

These options must be set in the `kuzzleio::options` object when [instantiating a new SDK]({{ site_base_path }}sdk-reference/cpp/1/kuzzle/constructor/#arguments).

### queue_max_size

An `unsigned long` defining the maximun size of the `offline_queue`.

Default value: `500`

### queue_ttl

An `unsigned` defining the time in milliseconds a queued request is kept in the `offline_queue`.

Default value: `120000`

### auto_queue

A `boolean` telling the SDK whether to automatically queue requests during the `offline` state or not.

Default value: `false`

### auto_replay

A `boolean` telling the SDK whether to automatically send or not the requests in the `offline_queue` on a
`reconnected` event.

Default value: `false`

### auto_reconnect

A `boolean` telling the SDK whether to automatically reconnect or not to Kuzzle after a connection loss.

Default value: `true`

### reconnection_delay 	

A `unsigned long` specifying the time in milliseconds between different reconnection attempts.

Default value: `10000`

### auto_resubscribe

A `boolean` telling the SDK whether to automatically renew or not all subscriptions on a reconnected event.

Default value: `true`

## Methods

### [startQueuing()]({{ site_base_path }}sdk-reference/cpp/1/kuzzle/start-queuing/)

Starts queuing requests when in `offline` state. Request will be put in the `offline_queue` instead of being discarded, until `stopQueuing` is called.
Works only in `offline` state when the `auto_queue` option is set to `false`. Call `playQueue` to send to Kuzzle the
requests in the queue, once the SDK state passes to `online`. Call `flushQueue` to empty the queue without sending the requests.

### [stopQueuing()]({{ site_base_path }}sdk-reference/cpp/1/kuzzle/stop-queuing)

Stop queuing the requests. Requests will no more be put in the `offline_queue`, they will be discarded.
Works only in the `offline` state, and if the `auto_queue` option is set to `false`.

### [playQueue()]({{ site_base_path }}sdk-reference/cpp/1/kuzzle/play-queue)

Sends to Kuzzle all the requests in the `offline_queue`. Works only if the SDK is not in a `offline` state, and if the 
`auto_replay` option is set to false.

### [flushQueue()]({{ site_base_path }}sdk-reference/cpp/1/kuzzle/flush-queue)

Empties the `offline_queue` without sending the requests to Kuzzle.
