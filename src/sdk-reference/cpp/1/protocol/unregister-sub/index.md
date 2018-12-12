---
layout: sdk.html.hbs
title: unregisterSub
description: Removes a subscription's notifications listener.
---

# registerSub

Removes a subscription's notifications listener.

## Arguments

```cpp
virtual void unregisterSub(const std::string&) = 0;
```

| Argument   | Type                      | Description
| ---------- |---------------------------|-------------------------------------------------- |
| `room_id` | <pre>const std::string&</pre>  | Subscription unique identifier

### listener

A c++11 lambda which takes a `const std::string` argument.
`EventListener` is defined as `const std::function<void(const std::string)>`.