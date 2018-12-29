---
layout: sdk.html.hbs
title: Events
description: SDK events system
order: 200
---

# Events

An event system allows to be notified when the SDK status changes. These events are issued by the [Kuzzle SDK object]({{site_base_path }}sdk-reference/cpp/1/kuzzle).

Subscription to these events is possible by specifying callbacks that will be executed when a specific event is issued by the SDK instance.  

These callbacks can be added and deleted respectively by the methods [addListener]({{site_base_path }}sdk-reference/cpp/1/kuzzle/add-listener) and [removeListener]({{site_base_path }}sdk-reference/cpp/1/kuzzle/remove-listener).

**Note:** listeners are called in the order of their insertion.

# Emitted Events

## EVENT_CONNECTED

Triggered when the SDK has successfully connected to Kuzzle.

## EVENT_DISCARDED

Triggered when Kuzzle rejects a request (e.g. request can't be parsed, request too large, ...).

**Callback argument**

A JSON string representing an object with the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `message` | <pre>std::string</pre> | Error description |
| `status` | <pre>int</pre> | Error code |
| `stack` | <pre>std::string</pre> | Stacktrace (development mode only) |

## EVENT_DISCONNECTED

Triggered when the current session has been unexpectedly disconnected.

## EVENT_LOGIN_ATTEMPT

Triggered when a login attempt completes, either with a success or a failure result.

**Callback arguments**

A JSON string representing an object with the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `success` | <pre>bool</pre> | Indicate if login attempt succeed |
| `error` | <pre>std::string</pre> | Error message when login fail |

## EVENT_NETWORK_ERROR

Triggered when the SDK has failed to connect to Kuzzle.  
This event does not trigger the offline mode.  

**Callback arguments**

A JSON string representing an object with the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `message` | <pre>std::string</pre> | Error description |
| `status` | <pre>int</pre> | Error code |
| `stack` | <pre>std::string</pre> | Stacktrace (development mode only) |

## EVENT_OFFLINE_QUEUE_POP

Triggered whenever a request is removed from the offline queue.

**Callback arguments**

A JSON string representing the [request]({{ site_base_path }}api/1/query-syntax/) removed from the queue.

## EVENT_OFFLINE_QUEUE_PUSH

Triggered whenever a request is added to the offline queue.

**Callback arguments**

A JSON string representing an object with the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `request` | <pre>std::string</pre> | JSON string representing [request]({{ site_base_path }}api/1/query-syntax/) added to the queue |    

## EVENT_QUERY_ERROR

Triggered whenever Kuzzle responds with an error

**Callback arguments**

A JSON string representing an object with the following properties:

| Property   | Type    | Description       |
| ---------- | ------- | ----------------- |
| `request` | <pre>std::string</pre> | JSON string representing the request that causing an error |    
| `error` | <pre>std::string</pre> | Error details |    

## EVENT_RECONNECTED

Triggered when the current session has reconnected to Kuzzle after a disconnection, and only if ``autoReconnect`` is set to ``true``.

## EVENT_TOKEN_EXPIRED

Triggered when Kuzzle rejects a request because the authentication token has expired.
