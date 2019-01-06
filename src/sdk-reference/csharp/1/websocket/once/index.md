---
layout: sdk.html.hbs
title: once
description: Attaches a listener to an event and removes it after it has been triggered once
---

# once

Attaches a listener to an event and removes it after it has been triggered once.

## Signature

```csharp
public override void once(Event arg0, SWIGTYPE_p_EventListener arg1);
```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------
| `event`    | <pre>kuzzleio::KuzzleEvent</pre>           | An enum representing the listener [event]({{ site_base_path }}sdk-reference/csharp/1/events/)
| `listener` | <pre>kuzzleio::EventListener\*</pre> | Pointer to a c++11 lambda

### event

One of the following event:

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

