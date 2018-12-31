---
layout: sdk.html.hbs
title: addListener
description: Add a listener to an event
---

# addListener

Adds a listener to an event.  
When an event is triggered, listeners are triggered in the order in which they were added.

## Signature

```java
io.kuzzle.sdk.KuzzleEventEmitter addListener(io.kuzzle.sdk.Event event, io.kuzzle.sdk.EventListener listener)
```

## Arguments

| Argument   | Type          | Description                                                                                            | Required |
| ---------- | ------------- | ------------------------------------------------------------------------------------------------------ | -------- |
| `event`    | io.kuzzle.sdk.Event | An enum representing the listened [event]({{ site_base_path }}sdk-reference/java/1/essentials/event-handling) | yes      |
| `listener` | io.kuzzle.sdk.EventListener | An instance of an `io.kuzzle.sdk.EventListener`                                                                      | yes      |

### **event**

One of the following event:

```java
io.kuzzle.sdk.Event.CONNECTED
io.kuzzle.sdk.Event.DISCARDED
io.kuzzle.sdk.Event.DISCONNECTED
io.kuzzle.sdk.Event.LOGIN_ATTEMPT
io.kuzzle.sdk.Event.NETWORK_ERROR
io.kuzzle.sdk.Event.OFFLINE_QUEUE_POP
io.kuzzle.sdk.Event.OFFLINE_QUEUE_PUSH
io.kuzzle.sdk.Event.QUERY_ERROR
io.kuzzle.sdk.Event.RECONNECTED
io.kuzzle.sdk.Event.JWT_EXPIRED
io.kuzzle.sdk.Event.ERROR
```

### **listener**

An instance of an `io.kuzzle.sdk.EventListener`.

## Return

The `io.kuzzle.sdk.Kuzzle` instance.

## Usage

[snippet=add-listener]
