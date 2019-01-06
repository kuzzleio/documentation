---
layout: sdk.html.hbs
title: listenerCount
description: Returns the number of listeners attached to an event
---

# listenerCount

Returns the number of listeners attached to an event.

## Signature

```csharp
public virtual int listenerCount(SWIGTYPE_p_Event arg0);

```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | -----------
| `event`    | <pre>kuzzleio::Event</pre>  | Enum representing the event to emit

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

## Return

The number of listeners attached to the specified event.
