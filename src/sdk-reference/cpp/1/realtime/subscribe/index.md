---
layout: sdk.html.hbs
algolia: true
title: subscribe
description:
order: 200
---

# subscribe

Subscribes by providing a set of `<filters>`: messages, document changes and, optionally, user events matching the provided `<filters>` will generate [real-time notifications]({{site_base_path}}api/1/notifications), sent to you in real-time by Kuzzle.

## Signature

```cpp
std::string subscribe(
  const std::string& index,
  const std::string& collection,
  const std::string& filters,
  kuzzleio::NotificationListener* listener,
  kuzzleio::room_options* options=nullptr
)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | const std::string& | Index name    |
| ``collection`` | const std::string& | Collection name    |
| ``filters`` | const std::string& | JSON string representing a set of filters following [Koncorde syntax]({{site_base_path}}kuzzle-dsl/1/essential/koncorde) |
| ``listener`` | kuzzleio::NotificationListener* | Listener function to handle notifications |
| ``options`` | kuzzleio::room_options* | A struct containing subscription options |

### **listener**

Listener function that will be called each time a new notifications is received.  
The listener will receive a [const kuzzleio::notification_result*]({{site_base_path}}sdk-reference/cpp/1/essentials/realtime-notifications) as only argument.  

### **options**

Additional subscription options.  

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `scope` | const char* | Subscribe to document entering or leaving the scope</br>Possible values: `all`, `in`, `out`, `none` | `all`  |
| `users` | const char* | Subscribe to users entering or leaving the room</br>Possible values: `all`, `in`, `out`, `none` | `none` |
| `subscribe_to_self` | bool | Subscribe to notifications fired by our own queries | `true`|
| `volatile` | const char* | JSON string representing subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/volatile-data) | `null` |

## Return

Return the room ID.

## Usage

*Simple subscription to document notifications*

[snippet=document-notifications]

*Subscription to document notifications with scope option*

[snippet=document-notifications-leave-scope]

*Subscription to message notifications*

[snippet=message-notifications]

*Subscription to user notifications*

[snippet=user-notifications]
