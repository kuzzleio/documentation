---
layout: sdk.html.hbs
algolia: true
title: Realtime notifications
description: List of realtime notifications sent by Kuzzle
order: 100
---
# Notifications

The [Realtime.Subscribe]({{ site_base_path }}sdk-reference/go/1/realtime/subscribe) method takes a channel for `types.KuzzleNotification` objects, whose content depend on the type of notification received.  

# Document notifications & messages

These notifications represent [documents changes & messages]({{ site_base_path }}api/1/notifications/#documents-changes-messages-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `Action` | string | API controller's action  |
| `Collection` | string | Data collection |
| `Controller` | string | API controller  |
| `Index` | string | Data index |
| `Protocol` | string | Network protocol used to modify the document |
| `Result` | *types.NotificationResult | Notification content |
| `RoomId` | string | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `Scope` | string | `in`: document enters (or stays) in the scope<br/>`out`: document leaves the scope |
| `Timestamp` | int | Timestamp of the event, in Epoch-millis format |
| `Type` | string | `document`: the notification type |
| `Volatile` | json.RawMessage | Request [volatile data]({{ site_base_path }}api/1/volatile-data/) |

The `Result` property has the following structure for document notifications & messages:

| Property | Type |Description       |
|--------------------|------|------------------|
| `id` | string | Document unique ID<br/>`null` if the notification is from a real-time message|
| `content` | json.RawMessage | A JSON String message or full document content. Not present if the event is about a document deletion |

## User

These notifications represent [user events]({{ site_base_path }}api/1/notifications/#user-events-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `Action` | string | API controller's action  |
| `Collection` | string | Data collection |
| `Controller` | string | API controller  |
| `Index` | string | Data index |
| `Protocol` | string | Network protocol used by the entering/leaving user |
| `Result` | *types.NotificationResult | Notification content |
| `RoomId` | string | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `Timestamp` | int | Timestamp of the event, in Epoch-millis format |
| `Type` | string | `user`: the notification type |
| `User` | string | `in`: a new user has subscribed to the same filters<br/>`out`: a user cancelled a shared subscription |
| `Volatile` | json.RawMessage | Request [volatile data]({{ site_base_path }}api/1/volatile-data/) |

The `Result` property has the following structure for user events:

| Property | Type |Description       |
|--------------------|------|------------------|
| `count` | int |  Updated users count sharing that same subscription |
