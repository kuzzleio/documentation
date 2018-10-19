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

```go
func (r *Realtime) Subscribe(
  index string,
  collection string,
  filters json.RawMessage,
  listener chan<- types.KuzzleNotification,
  options types.RoomOptions
) (*types.SubscribeResult, error)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``filters`` | json.RawMessage | A set of filters following [Koncorde syntax]({{site_base_path}}kuzzle-dsl/1/essential/koncorde) |
| ``listener`` | chan<- types.KuzzleNotification | Channel receiving the notification |
| ``options`` | types.RoomOptions | A struct containing subscription options |

###### **listener**

A channel for [types.KuzzleNotification]({{site_base_path}}sdk-reference/cpp/1/essentials/realtime-notifications) objects.
The channel will receive an object each time a new notifications is received.  

### **options**

Additional subscription options.

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Scope` | const char* | Subscribe to document entering or leaving the scope</br>Possible values: `all`, `in`, `out`, `none` | `all`  |
| `Users` | const char* | Subscribe to users entering or leaving the room</br>Possible values: `all`, `in`, `out`, `none` | `none` |
| `SubscribeToSelf` | bool | Subscribe to notifications fired by our own queries | `true`|
| `Volatile` | const char* | JSON string representing subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/volatile-data) | `null` |

## Return

Return an error if something was wrong or a `types.SubscribeResult` containing the following properties:

| Property    | Type    | Description |
|--------------|---------|-------------|
| ``room`` | string | The room ID    |
| ``channel`` | string | The channel ID    |

## Usage

*Simple subscription to document notifications*

[snippet=document-notifications]

*Subscription to document notifications with scope option*

[snippet=document-notifications-leave-scope]

*Subscription to message notifications*

[snippet=message-notifications]

*Subscription to user notifications*

[snippet=user-notifications]
