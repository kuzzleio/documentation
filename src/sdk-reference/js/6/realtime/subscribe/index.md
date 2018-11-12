---
layout: sdk.html.hbs
algolia: true
title: subscribe
description: Subscribe to real-time notifications
---

# subscribe

Subscribes by providing a set of filters: messages, document changes and, optionally, user events matching the provided filters will generate [real-time notifications]({{site_base_path}}api/1/notifications), sent to you in real-time by Kuzzle.

## Arguments

```javascript
 subscribe (index, collection, filters, callback, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``filters`` | object | Set of filters following [Koncorde syntax]({{site_base_path}}kuzzle-dsl/1/essential/koncorde) |
| ``callback`` | Function | Callback function to handle notifications |
| ``options`` | object | An object containing query options |

### callback

Callback function that will be called each time a new notifications is received.
The callback will receive the [notifications object]({{site_base_path}}sdk-reference/js/6/essentials/realtime-notifications) as only argument.

### options

Additional subscription options.

| Property   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `scope` | string<br/>(`all`) | Subscribe to document entering or leaving the scope</br>Possible values: `all`, `in`, `out`, `none` |
| `users` | string<br/>(`none`) | Subscribe to users entering or leaving the room</br>Possible values: `all`, `in`, `out`, `none` |
| `subscribeToSelf` | boolean<br/>(`true`) | Subscribe to notifications fired by our own queries |
| `volatile` | object<br/>(`null`) | subscription information, used in [user join/leave notifications]({{site_base_path}}api/1/volatile-data) |

## Resolve

Resolves to a string containing the room ID

## Usage

*Simple subscription to document notifications*

[snippet=document-notifications]

*Subscription to document notifications with scope option*

[snippet=document-notifications-leave-scope]

*Subscription to message notifications*

[snippet=message-notifications]

*Subscription to user notifications*

[snippet=user-notifications]
