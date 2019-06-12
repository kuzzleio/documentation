---
code: false
type: page
title: Events
description: SDK events system
order: 100
---

# Events

An event system allows to be notified when the SDK status changes. These events are issued by the [Kuzzle SDK object](/sdk/cpp/1/core-classes/kuzzle).

Subscription to these events is possible by specifying callbacks that will be executed when a specific event is issued by the SDK instance.

These callbacks can be added by the method [addListener](/sdk/cpp/1/core-classes/kuzzle/).

**Note:** listeners are called in the order of their insertion.

# Emitted Events

## KUZZLE_EVENT_CONNECTED

Triggered when the SDK has successfully connected to Kuzzle.

## KUZZLE_EVENT_DISCARDED

Triggered when Kuzzle rejects a request (e.g. request can't be parsed, request too large, ...).

**Callback argument**

A JSON string representing an object with the following properties:

| Property  | Type                   | Description                        |
| --------- | ---------------------- | ---------------------------------- |
| `message` | <pre>std::string</pre> | Error description                  |
| `status`  | <pre>int</pre>         | Error code                         |
| `stack`   | <pre>std::string</pre> | Stacktrace (development mode only) |

## KUZZLE_EVENT_DISCONNECTED

Triggered when the current session has been unexpectedly disconnected.

## KUZZLE_EVENT_LOGIN_ATTEMPT

Triggered when a login attempt completes, either with a success or a failure result.

**Callback arguments**

A JSON string representing an object with the following properties:

| Property  | Type                   | Description                       |
| --------- | ---------------------- | --------------------------------- |
| `success` | <pre>bool</pre>        | Indicate if login attempt succeed |
| `error`   | <pre>std::string</pre> | Error message when login fail     |

## KUZZLE_EVENT_NETWORK_ERROR

Triggered when the SDK has failed to connect to Kuzzle.
This event does not trigger the offline mode.

**Callback arguments**

A JSON string representing an object with the following properties:

| Property  | Type                   | Description                        |
| --------- | ---------------------- | ---------------------------------- |
| `message` | <pre>std::string</pre> | Error description                  |
| `status`  | <pre>int</pre>         | Error code                         |
| `stack`   | <pre>std::string</pre> | Stacktrace (development mode only) |

## KUZZLE_EVENT_OFFLINE_QUEUE_POP

Triggered whenever a request is removed from the offline queue.

**Callback arguments**

A JSON string representing the [request](/core/1/api/essentials/query-syntax/) removed from the queue.

## KUZZLE_EVENT_OFFLINE_QUEUE_PUSH

Triggered whenever a request is added to the offline queue.

**Callback arguments**

A JSON string representing an object with the following properties:

| Property  | Type                   | Description                                                                                 |
| --------- | ---------------------- | ------------------------------------------------------------------------------------------- |
| `request` | <pre>std::string</pre> | JSON string representing [request](/core/1/api/essentials/query-syntax/) added to the queue |

## KUZZLE_EVENT_QUERY_ERROR

Triggered whenever Kuzzle responds with an error

**Callback arguments**

A JSON string representing an object with the following properties:

| Property  | Type                   | Description                                                |
| --------- | ---------------------- | ---------------------------------------------------------- |
| `request` | <pre>std::string</pre> | JSON string representing the request that causing an error |
| `error`   | <pre>std::string</pre> | Error details                                              |

## KUZZLE_EVENT_RECONNECTED

Triggered when the current session has reconnected to Kuzzle after a disconnection, and only if `autoReconnect` is set to `true`.

## KUZZLE_EVENT_TOKEN_EXPIRED

Triggered when Kuzzle rejects a request because the authentication token has expired.
