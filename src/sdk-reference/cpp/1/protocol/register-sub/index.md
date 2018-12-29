---
layout: sdk.html.hbs
title: registerSub
description: Used when subscribing to store a listener.
---

# registerSub

Attaches a notifications listener to an existing subscription.

## Signature

```cpp
virtual void registerSub(
  const std::string& channel, 
  const std::string& room_id, 
  const std::string& filters, 
  bool subscribe_to_self, 
  NotificationListener* listener) = 0;
```

## Arguments

| Argument   | Type                      | Description
| ---------- |---------------------------|--------------------------------------------------------------------- |
| `channel`    | <pre>const std::string&</pre>           | Subscription channel identifier
| `room_id` | <pre>const std::string&</pre>  | Subscription room identifier
| `filters` | <pre>const std::string&</pre> | Subscription filters
| `subscribe_to_self` | <pre>bool</pre> | Subscribe to notifications fired by our own queries
| `listener` | <pre>NotificationListener\*</pre> | A pointer to the listener

### listener

A c++11 lambda which takes a `const std::string` argument.
`EventListener` is defined as `const std::function<void(const std::string)>`.
