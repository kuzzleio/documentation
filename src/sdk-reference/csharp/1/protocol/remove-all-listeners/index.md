---
layout: sdk.html.hbs
title: removeAllListeners
description: Removes all listeners from an event
---

# removeAllListeners

Removes all listeners from an event.

## Signature

```csharp
public virtual void removeAllListeners(SWIGTYPE_p_Event arg0);

```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | ---------------------------
| `event`    | <pre>Kuzzleio::Event</pre>  | Enum representing the event to emit
| `listener` | <pre>Kuzzleio::EventListener\*</pre> | Pointer to a c++11 lambda

### event

One of the following [event]({{ site_base_path }}sdk-reference/csharp/1/events):

```csharp
EVENT_CONNECTED,
EVENT_DISCARDED,
EVENT_DISCONNECTED,
EVENT_LOGIN_ATTEMPT,
EVENT_NETWORK_ERROR,
EVENT_OFFLINE_QUEUE_POP,
EVENT_OFFLINE_QUEUE_PUSH,
EVENT_QUERY_ERROR,
EVENT_RECONNECTED,
EVENT_JWT_EXPIRED,
EVENT_ERROR
```