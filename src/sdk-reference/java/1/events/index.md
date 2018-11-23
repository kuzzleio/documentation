---
layout: sdk.html.hbs
algolia: true
title: Events
description: SDK events system
order: 200
---

# Events

An event system allows to be notified when the SDK status changes. These events are issued by the [Kuzzle SDK object]({{site_base_path }}sdk-reference/java/1/kuzzle).

Subscription to these events is possible by specifying callbacks that will be executed when a specific event is issued by the SDK instance.  

These callbacks can be added and deleted respectively by the methods [addListener]({{site_base_path }}sdk-reference/java/1/kuzzle/add-listener) and [removeListener]({{site_base_path }}sdk-reference/java/1/kuzzle/remove-listener).

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
| `message` | String | Error description |
| `status` | Integer | Error code |
| `stack` | String | Stacktrace (development mode only) |

## disconnected

Triggered when the current session has been unexpectedly disconnected.

## loginAttempt

Triggered when a login attempt completes, either with a success or a failure result.

**Callback arguments**

A String representing a JSON object containing the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `success` | boolean | Indicate if login attempt succeed |
| `error` | String | Error message when login fail |

## networkError

Triggered when the SDK has failed to connect to Kuzzle.  
This event does not trigger the offline mode.  

**Callback arguments**

A String representing a JSON object containing the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `message` | String | Error description |
| `status` | Integer | Error code |
| `stack` | String | Stacktrace (development mode only) |

## offlineQueuePop

Triggered whenever a request is removed from the offline queue.

**Callback arguments**

A String representing a JSON object containing the [request]({{ site_base_path }}api/1/query-syntax/) removed from the queue.

## offlineQueuePush

Triggered whenever a request is added to the offline queue.

**Callback arguments**

A String representing a JSON object containing the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `request` | JSON object | The [request]({{ site_base_path }}api/1/query-syntax/) added to the queue |    

## queryError

Triggered whenever Kuzzle responds with an error

**Callback arguments**

A String representing a JSON object containing the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `request` | JSON object | The request that causing an error |    
| `error` | JSON object | The error details |    

## reconnected

Triggered when the current session has reconnected to Kuzzle after a disconnection, and only if ``autoReconnect`` is set to ``true``.

## tokenExpired

Triggered when Kuzzle rejects a request because the authentication token has expired.
