---
layout: sdk.html.hbs
title: registerSub
description: Used when subscribing to store a listener.
---

# registerSub

Attaches a notifications listener to an existing subscription.

## Signature

```csharp
public virtual void registerSub(
    string arg0, 
    string arg1, 
    string arg2, 
    bool arg3, 
    SWIGTYPE_p_NotificationListener arg4);

```

## Arguments

| Argument   | Type                      | Description
| ---------- |---------------------------|--------------------------------------------------------------------- |
| `channel`    | <pre>string</pre>           | Subscription channel identifier
| `room_id` | <pre>string</pre>  | Subscription room identifier
| `filters` | <pre>string</pre> | Subscription filters
| `subscribe_to_self` | <pre>bool</pre> | Subscribe to notifications fired by our own queries
| `listener` | <pre>NotificationListener\*</pre> | A pointer to the listener

### listener

A c++11 lambda which takes a `string` argument.
`EventListener` is defined as `std::function<void(string)>`.
