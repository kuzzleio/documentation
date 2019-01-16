---
layout: sdk.html.hbs
title: Subscribe
description: Subscribe to real-time notifications
---

# Subscribe

Subscribes by providing a set of filters: messages, document changes and, optionally, user events matching the provided filters will generate [real-time notifications]({{site_base_path}}api/1/essentials/notifications), sent to you in real-time by Kuzzle.

## Arguments

```go
func (r *Realtime) Subscribe(
  index string,
  collection string,
  filters json.RawMessage,
  listener chan<- types.NotificationResult,
  options types.RoomOptions
) (*types.SubscribeResult, error)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>string</pre> | Index name    |
| `collection` | <pre>string</pre> | Collection name    |
| `filters` | <pre>json.RawMessage</pre> | A set of filters following [Koncorde syntax]({{site_base_path}}koncorde) |
| `listener` | <pre>chan<- types.NotificationResult</pre> | Channel receiving the notification |
| `options` | <pre>types.RoomOptions</pre> | A struct containing subscription options |

### listener

A channel for [types.NotificationResult]({{site_base_path}}sdk-reference/go/1/realtime-notifications) objects.
The channel will receive an object each time a new notifications is received.

### options

Additional subscription options.

| Property   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `scope` | <pre>string</pre><br/>(`all`) | Subscribe to document entering or leaving the scope</br>Possible values: `all`, `in`, `out`, `none` |
| `users` | <pre>string</pre><br/>(`none`) | Subscribe to users entering or leaving the room</br>Possible values: `all`, `in`, `out`, `none` |
| `subscribeToSelf` | <pre>bool</pre><br/>(`true`) | Subscribe to notifications fired by our own queries |
| `volatile` | <pre>json.RawMessage</pre><br/>(`{}`) | subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/essentials/volatile-data/) |


## Return

Return an error if something was wrong or a `types.SubscribeResult` containing the following properties:

| Property    | Type    | Description |
|--------------|---------|-------------|
| ``Room`` | <pre>string</pre> | The room ID    |
| ``Channel`` | <pre>string</pre> | The channel ID    |

## Usage

*Simple subscription to document notifications*

[snippet=document-notifications]

*Subscription to document notifications with scope option*

[snippet=document-notifications-leave-scope]

*Subscription to message notifications*

[snippet=message-notifications]

*Subscription to user notifications*

[snippet=user-notifications]
