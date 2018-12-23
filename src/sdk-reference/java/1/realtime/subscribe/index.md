---
layout: sdk.html.hbs
title: subscribe
description: Subscribe to real-time notifications
---

# subscribe

Subscribes by providing a set of filters: messages, document changes and, optionally, user events matching the provided filters will generate [real-time notifications]({{site_base_path}}api/1/notifications), sent to you in real-time by Kuzzle.

## Arguments

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

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>String</pre> | Index name    |
| `collection` | <pre>String</pre> | Collection name    |
| `filters` | <pre>String</pre> | JSON string representing a set of filters following [Koncorde syntax]({{site_base_path}}kuzzle-dsl/1/essential/koncorde) |
| `listener` | <pre><a href="{{site_base_path}}sdk-reference/java/1/essentials/realtime-notifications">io.kuzzle.sdk.NotificationListener</a></pre> | Listener function to handle notifications |
| `options` | <pre>io.kuzzle.sdk.RoomOptions</pre> | Subscription options |

### options

Additional subscription options.

| Property   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `scope` | <pre>String</pre><br/>(`all`) | Subscribe to document entering or leaving the scope</br>Possible values: `all`, `in`, `out`, `none` |
| `users` | <pre>String</pre><br/>(`none`) | Subscribe to users entering or leaving the room</br>Possible values: `all`, `in`, `out`, `none` |
| `subscribeToSelf` | <pre>boolean</pre><br/>(`true`) | Subscribe to notifications fired by our own queries |
| `volatile` | <pre>String</pre><br/>(`null`) | JSON string representing subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/essentials/volatile-data/) |

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
