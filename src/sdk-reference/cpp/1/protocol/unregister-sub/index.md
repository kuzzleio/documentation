---
layout: sdk.html.hbs
title: unregisterSub
description: Used when unsubscribing to store a listener.
---

# registerSub

Used when unsubscribing to remove a listener. 

## Signature

```cpp
    virtual void unregisterSub(const std::string&) = 0;
```

## Arguments

| Argument   | Type                      | Description
| ---------- |------------------------------------------------------------------------------- |
| `room_id` | <pre>const std::string&</pre>  | A string representing the roomId of the subscription

### **listener**

A c++11 lambda which take a `const std::string&`
`EventListener` is defined as `const std::function<void(const std::string&)>`.
