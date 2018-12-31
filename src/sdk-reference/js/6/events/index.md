---
layout: sdk.html.hbs
title: Events
description: SDK events system
order: 200
---

# Events

An event system allows to be notified when the SDK status changes. These events are issued by the [Kuzzle SDK object]({{site_base_path }}sdk-reference/js/6/kuzzle).

Subscription to these events is possible by registering callbacks that will be called when a specific event is issued by the SDK instance.

These callbacks can be added and deleted respectively by the methods [addListener]({{site_base_path }}sdk-reference/js/6/kuzzle/add-listener) and [removeListener]({{site_base_path }}sdk-reference/js/6/kuzzle/remove-listener).

**Note:** listeners are called in the order of their insertion.

# Emitted Events

## connected

Triggered when the SDK has successfully connected to Kuzzle.

## discarded

Triggered when Kuzzle discards a request, typically if no connection is established and the request is not queuable, either because the offline mode is not set or if set explicitely.

**Callback arguments:**

`@param {object} request`: the discarded [request]({{ site_base_path }}api/1/essentials/query-syntax/)

## disconnected

Triggered when the current session has been unexpectedly disconnected.

## loginAttempt

Triggered when a login attempt completes, either with a success or a failure result.

**Callback arguments:**

`@param {object} data`

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `success` | <pre>boolean</pre> | Indicate if login attempt succeed |
| `error` | <pre>string</pre> | Error message when login fail |

## networkError

Triggered when the SDK has failed to connect to Kuzzle.

**Callback arguments:**

`@param {Error} error`

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `message` | <pre>string</pre> | Error description |
| `status` | <pre>number</pre> | Error code |
| `stack` | <pre>string</pre> | Stacktrace (development mode only) |

## offlineQueuePop

Triggered whenever a request is removed from the offline queue.

**Callback arguments:**

`@param {object} request`: the [request]({{ site_base_path }}api/1/essentials/query-syntax/) removed from the queue


## offlineQueuePush

Triggered whenever a request is added to the offline queue.

**Callback arguments:**

`@param {object} data`

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `request` | <pre>object</pre> | [Request]({{ site_base_path }}api/1/essentials/query-syntax//) added to the queue |

## queryError

Triggered whenever Kuzzle responds with an error

**Callback arguments:**

`@param {object} data`

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `request` | <pre>object</pre> | Request that causing an error |
| `error` | <pre>Error</pre> | Error details |

## reconnected

Triggered when the current session has reconnected to Kuzzle after a disconnection, and only if ``autoReconnect`` is set to ``true``.

## tokenExpired

Triggered when Kuzzle rejects a request because the authentication token has expired.
