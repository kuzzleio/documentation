---
layout: sdk.html.hbs
title: registerSub
description: Used when subscribing to store a listener.
---

# registerSub

Used when subscribing to store a listener. 

## Signature

```cpp
    virtual void registerSub(const std::string& channel, const std::string& room_id, const std::string& filters, bool subscrive_to_self, NotificationListener* listener) = 0;
```

## Arguments

| Argument   | Type                      | Description
| ---------- |---------------------------------------------------------------------------------------------- |
| `channel`    | <pre>const std::string&</pre>           | A string representing the channel of the subscription
| `room_id` | <pre>const std::string&</pre>  | A string representing the roomId of the subscription
| `filters` | <pre>const std::string&</pre> | A JSON string representing the filters of the subscription
| `subscribe_to_self` | <pre>bool</pre> | Subscribe to notifications fired by our own queries
| `listener` | <pre>NotificationListener*</pre> | A pointer to the listener

### **listener**

A c++11 lambda which take a `const std::string&`
`EventListener` is defined as `const std::function<void(const std::string)>`.
