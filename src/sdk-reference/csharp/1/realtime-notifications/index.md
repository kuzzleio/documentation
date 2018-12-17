---
layout: sdk.html.hbs
title: Realtime notifications
description: List of realtime notifications sent by Kuzzle
order: 300
---
# Notifications

The [realtime.subscribe]({{ site_base_path }}sdk-reference/csharp/1/realtime/subscribe) method takes a listener of type `NotificationListener`.  
That listener is called with a `NotificationResult` argument, pointing to an object whose content depends on the type of notification received.

## Document & messages

These `NotificationResult` represent [documents changes & messages]({{ site_base_path }}api/1/notifications/#documents-changes-messages-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | string | API controller's action  |
| `collection` | string | Data collection |
| `controller` | string | API controller  |
| `index` | string | Data index |
| `protocol` | string | Network protocol used to modify the document |
| `result` | NotificationContent | Notification content |
| `room_id` | string | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `scope` | string | `in`: document enters (or stays) in the scope<br/>`out`: document leaves the scope |
| `timestamp` | long | Timestamp of the event, in Epoch-millis format |
| `n_type` | string | `document`: the notification type |
| `volatiles` | string | JSON String representing request [volatile data]({{ site_base_path }}api/1/volatile-data/) |

The `NotificationContent` class has the following properties for document notifications & messages:

| Property | Type |Description       |
|--------------------|------|------------------|
| `id` | string | Document unique ID<br/>`null` if the notification is from a real-time message|
| `content` | string | A JSON String message or full document content. Not present if the event is about a document deletion |

## User

These `NotificationResult` represent [user events]({{ site_base_path }}api/1/notifications/#user-events-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | string | API controller's action  |
| `collection` | string | Data collection |
| `controller` | string | API controller  |
| `index` | string | Data index |
| `protocol` | string | Network protocol used by the entering/leaving user |
| `result` | NotificationContent | Notification content |
| `room_id` | string | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `timestamp` | long | Timestamp of the event, in Epoch-millis format |
| `n_type` | string | `user`: the notification type |
| `user` | string | `in`: a new user has subscribed to the same filters<br/>`out`: a user cancelled a shared subscription |
| `volatiles` | string | JSON String representing request [volatile data]({{ site_base_path }}api/1/volatile-data/) |

The `NotificationContent` class has the following properties for document notifications & messages:

| Property | Type |Description       |
|--------------------|------|------------------|
| `count` | int |  Updated users count sharing that same subscription |
