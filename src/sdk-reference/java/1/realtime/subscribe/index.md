---
layout: sdk.html.hbs
algolia: true
title: subscribe
description:
order: 200
---

# subscribe

Subscribes by providing a set of filters: messages, document changes and, optionally, user events matching the provided filters will generate [real-time notifications]({{site_base_path}}api/1/notifications), sent to you in real-time by Kuzzle.

## Signature

```java
public String subscribe(
  String index,
  String collection,
  String filters,
  io.kuzzle.sdk.NotificationListener listener,
  io.kuzzle.sdk.RoomOptions options
)
public String subscribe(
  String index,
  String collection,
  String filters,
  io.kuzzle.sdk.NotificationListener listener
)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | String | Index name    |
| ``collection`` | String | Collection name    |
| ``filters`` | String | JSON string representing a set of filters following [Koncorde syntax]({{site_base_path}}kuzzle-dsl/1/essential/koncorde) |
| ``listener`` | [io.kuzzle.sdk.NotificationListener]({{site_base_path}}sdk-reference/java/1/essentials/realtime-notifications) | Listener function to handle notifications |
| ``options`` | io.kuzzle.sdk.RoomOptions | Subscription options |

### **options**

Additional subscription options.

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `scope` | String | Subscribe to document entering or leaving the scope</br>Possible values: `all`, `in`, `out`, `none` | `all`  |
| `user` | String | Subscribe to users entering or leaving the room</br>Possible values: `all`, `in`, `out`, `none` | `none` |
| `subscribeToSelf` | boolean | Subscribe to notifications fired by our own queries | `true`|
| `volatile` | String | JSON string representing subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/volatile-data) | `null` |

## Return

Return the room ID.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

*Simple subscription to document notifications*

[snippet=document-notifications]

*Subscription to document notifications with scope option*

[snippet=document-notifications-leave-scope]

*Subscription to message notifications*

[snippet=message-notifications]

*Subscription to user notifications*

[snippet=user-notifications]
