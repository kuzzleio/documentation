---
layout: sdk.html.hbs
title: unregisterSub
description: Removes a subscription's notifications listener.
---

# registerSub

Removes a subscription's notifications listener.

## Signature

```cpp
    virtual void unregisterSub(const std::string&) = 0;
```

## Arguments

| Argument   | Type                      | Description
| ---------- |---------------------------|-------------------------------------------------- |
| `room_id` | <pre>const std::string&</pre>  | Subscription unique identifier

### **listener**

A c++11 lambda which take a `const std::string&`
`EventListener` is defined as `const std::function<void(const std::string&)>`.
