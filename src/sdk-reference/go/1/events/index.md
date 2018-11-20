---
layout: sdk.html.hbs
algolia: true
title: Events
description: SDK events system
order: 200
---


# Events

An event system allows to be notified when the SDK status changes. These events are issued by the [Kuzzle SDK object]({{site_base_path }}sdk-reference/go/1/kuzzle).

Subscription to these events is possible by passing a channel that will receive data when a specific event is issued by the SDK instance.  

These channels can be added and deleted respectively by the methods [addListener]({{site_base_path }}sdk-reference/go/1/kuzzle/add-listener) and [removeListener]({{site_base_path }}sdk-reference/go/1/kuzzle/remove-listener).

**Note:** channels receive data in the order of their insertion.

# Emitted Events

## connected

Triggered when the SDK has successfully connected to Kuzzle.

## discarded

Triggered when Kuzzle rejects a request (e.g. request can't be parsed, request too large, ...).

**Channel signature:** `chan<- *types.KuzzleResponse)`

## disconnected

Triggered when the current session has been unexpectedly disconnected.

**Channel signature:** `chan<- interface{}` (will receive nil)

## loginAttempt

Triggered when a login attempt completes, either with a success or a failure result.

**Channel signature:** `chan<- *types.LoginAttempt`

## networkError

Triggered when the SDK has failed to connect to Kuzzle.  
This event does not trigger the offline mode.  

**Channel signature:** `chan<- error`

## offlineQueuePop

Triggered whenever a request is removed from the offline queue.

**Channel signature:** `chan<- *types.QueryObject`

## offlineQueuePush

Triggered whenever a request is added to the offline queue.

**Channel signature:** `chan<- *types.QueryObject`

## queryError

Triggered whenever Kuzzle responds with an error

**Channel signature:** `chan<- *types.QueryObject`

## reconnected

Triggered when the current session has reconnected to Kuzzle after a disconnection, and only if ``AutoReconnect`` is set to ``true``.

**Channel signature:** `chan<- interface{}` (will receive nil)

## tokenExpired

Triggered when Kuzzle rejects a request because the authentication token has expired.

**Channel signature:** `chan<- interface{}` (will receive nil)
