---
layout: sdk.html
algolia: true
title: addListener
description: Add a listener to an event
order: 200
---

# addListener

Adds a listener to an event.  
When an event is triggered, listeners are triggered in the order in which they were added.

## Signature

```java
KuzzleEventEmitter addListener(Event event, EventListener listener)
```

## Arguments

| Argument   | Type          | Description                                                                                            | Required |
| ---------- | ------------- | ------------------------------------------------------------------------------------------------------ | -------- |
| `event`    | Event         | An enum representing the listened [event]({{ site_base_path }}sdk-reference/essentials/event-handling) | yes      |
| `listener` | EventListener | An instance of an `EventListener`                                                                      | yes      |

### **event**

One of the following event:

```java
Event.CONNECTED
Event.DISCARDED
Event.DISCONNECTED
Event.LOGIN_ATTEMPT
Event.NETWORK_ERROR
Event.OFFLINE_QUEUE_POP
Event.OFFLINE_QUEUE_PUSH
Event.QUERY_ERROR
Event.RECONNECTED
Event.JWT_EXPIRED
Event.ERROR
```

### **listener**

An instance of an `EventListener`.

## Return

The `Kuzzle` instance.

## Usage

[code-example=add-listener]
