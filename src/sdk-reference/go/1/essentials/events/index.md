---
layout: sdk.html.hbs
algolia: true
title: Events
description: SDK events system
order: 100
---

# Events

An event system allows to be notified when the SDK status changes. These events are issued by the [Kuzzle SDK object]({{site_base_path }}sdk-reference/go/1/kuzzle).

Subscription to these events is possible by specifying callbacks that will be executed when a specific event is issued by the SDK instance.  

These callbacks can be added and deleted respectively by the methods [addListener]({{site_base_path }}sdk-reference/go/1/kuzzle/add-listener) and [removeListener]({{site_base_path }}sdk-reference/go/1/kuzzle/remove-listener).

**Note:** listeners are called in the order of their insertion.

# Emitted Events

## connected

Triggered when the SDK has successfully connected to Kuzzle.

## discarded

Triggered when Kuzzle rejects a request (e.g. request can't be parsed, request too large, ...).

**Callback argument**

A String representing a JSON object containing the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `message` | string | Error description |
| `status` | int | Error code |
| `stack` | string | Stacktrace (development mode only) |

## disconnected

Triggered when the current session has been unexpectedly disconnected.

**Callback signature**

`callback()`

## loginAttempt

Triggered when a login attempt completes, either with a success or a failure result.

**Callback signature**

`callback(result types.LoginAttempt)`

## networkError

Triggered when the SDK has failed to connect to Kuzzle.  
This event does not trigger the offline mode.  

**Callback signature**

`callback(err error)`

## offlineQueuePop

Triggered whenever a request is removed from the offline queue.

**Callback signature**

`callback(query types.QueryObject)`

## offlineQueuePush

Triggered whenever a request is added to the offline queue.

**Callback signature**

`callback(query types.QueryObject)`

## queryError

Triggered whenever Kuzzle responds with an error

**Callback signature**

`callback(query types.QueryObject)`

## reconnected

Triggered when the current session has reconnected to Kuzzle after a disconnection, and only if ``AutoReconnect`` is set to ``true``.

**Callback signature**

`callback()`

## tokenExpired

Triggered when Kuzzle rejects a request because the authentication token has expired.

**Callback signature**

`callback()`
