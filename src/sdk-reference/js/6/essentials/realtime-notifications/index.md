---
layout: sdk.html.hbs
algolia: true
title: Realtime notifications
description: List of realtime notifications sent by Kuzzle
order: 100
---
# Notifications

The [realtime.subscribe]({{ site_base_path }}sdk-reference/js/6/realtime/subscribe) method takes a callback argument, called with a notification object, whose properties depends on the type of notification received.

# Document notifications & messages

These notifications represent [documents changes & messages]({{ site_base_path }}api/1/notifications/#documents-changes-messages-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | String | API controller's action  |
| `collection` | String | Data collection |
| `controller` | String | API controller  |
| `index` | String | Data index |
| `protocol` | String | Network protocol used to modify the document |
| `result` | object | Notification content |
| `room` | String | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `scope` | String | `in`: document enters (or stays) in the scope<br/>`out`: document leaves the scope |
|`timestamp` | Number | Timestamp of the event, in Epoch-millis format |
| `type` | String | `document`: the notification type |
| `volatile` | object | Request [volatile data]({{ site_base_path }}api/1/volatile-data/) |

The `result` object is the notification content, and it has the following structure:

| Property | Type |Description       |
|--------------------|------|------------------|
| `_id` | String | Document unique ID<br/>`null` if the notification is from a real-time message|
| `_source` | object | The message or full document content. Not present if the event is about a document deletion |

# User notifications

These notifications represents [user events]({{ site_base_path }}api/1/notifications/#user-events-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | String | API controller's action  |
| `collection` | String | Data collection |
| `controller` | String | API controller  |
| `index` | String | Data index |
| `protocol` | String | Network protocol used by the entering/leaving user |
| `result` | object | Notification content |
| `room` | String | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
|`timestamp` | Number | Timestamp of the event, in Epoch-millis format |
| `type` | String | `user`: the notification type |
| `user` | String | `in`: a new user has subscribed to the same filters<br/>`out`: a user cancelled a shared subscription |
| `volatile` | object | Request [volatile data]({{ site_base_path }}api/1/volatile-data/) |

The `result` object is the notification content, and it has the following structure:

| Property | Type |Description       |
|--------------------|------|------------------|
| `count` | Number |  Updated users count sharing that same subscription |

# Server notifications

These notifications represents [server events]({{ site_base_path }}api/1/notifications/#server-events-default).

| Property | Type | Value |
|--------------------|------|------------------|
| `message` | String | Server message explaining why this notification has been triggered |
| `type` | String | `TokenExpired`: notification type |
