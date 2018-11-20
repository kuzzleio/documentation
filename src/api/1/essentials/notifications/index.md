---
layout: full.html.hbs
algolia: true
title: Real-time Notifications
order: 500
---


# Real-time Notifications

Clients can [subscribe]({{ site_base_path }}api/1/controller-realtime/subscribe) to documents, messages and events, in order to receive a notification whenever a change occurs matching the subscription scope.


## User events

User notifications about users are triggered by the following events:

* A user subscribes to [the same room]({{ site_base_path }}kuzzle-dsl/1/roomid/)
* A user leaves that room

These notifications are sent only if the `users` argument is set to any other value than the default `none` one (see [subscription request]({{ site_base_path }}api/1/controller-realtime/subscribe/)).

### Format

| Property | Type |Description       |
|
## Server events

Server notifications are triggered by global events, and they are sent to all of a client's subscriptions at the same time.

Currently, the only event generating a server notification is when an [authentication token]({{ site_base_path }}guide/1/essentials/user-authentication) has expired, closing the subscription.

Other events may be added in the future.

### Format

| Property | Type | Value |
|--------------------|------|------------------|
| `message` | string | Server message explaining why this notification has been triggered |
| `type` | string | `TokenExpired`: notification type |

### Example

```js
{
  "message": "Authentication Token Expired",
  "type": "TokenExpired"
}
```
