---
layout: sdk.html.hbs
title: Realtime notifications
description: List of realtime notifications sent by Kuzzle
order: 300
---
# Notifications

The [realtime:subscribe]({{ site_base_path }}sdk-reference/csharp/1/realtime/subscribe) method takes a listener of type `NotificationListener`.  
That listener is called with a `NotificationResult` argument, pointing to an object whose content depends on the type of notification received.

## Document & messages

These `NotificationResult` represent [documents changes & messages]({{ site_base_path }}api/1/essentials/notifications#documents-changes-messages-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | <pre>string</pre> | API controller's action  |
| `collection` | <pre>string</pre> | Collection name |
| `controller` | <pre>string</pre> | API controller  |
| `index` | <pre>string</pre> | Index name |
| `protocol` | <pre>string</pre> | Network protocol used to modify the document |
| `result` | <pre>NotificationContent\*</pre> | Notification content |
| `room_id` | <pre>string</pre> | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `scope` | <pre>string</pre> | `in`: document enters (or stays) in the scope<br/>`out`: document leaves the scope |
| `timestamp` | <pre>long</pre> | Timestamp of the event, in Epoch-millis format |
| `n_type` | <pre>string</pre> | `document`: the notification type |
| `volatiles` | <pre>string</pre> | JSON String representing request [volatile data]({{ site_base_path }}api/1/essentials/volatile-data//) |

The `NotificationContent` class has the following properties for document notifications & messages:

| Property | Type |Description       |
|--------------------|------|------------------|
| `id` | <pre>string</pre> | Document unique ID<br/>`null` if the notification is from a real-time message|
| `content` | <pre>string</pre> | JSON String representing the message or the full document content. Not present if the event is about a document deletion |

## User

These `NotificationResult` represent [user events]({{ site_base_path }}api/1/essentials/notifications#user-events-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | <pre>string</pre> | API controller's action  |
| `collection` | <pre>string</pre> | Collection name |
| `controller` | <pre>string</pre> | API controller  |
| `index` | <pre>string</pre> | Index name |
| `protocol` | <pre>string</pre> | Network protocol used by the entering/leaving user |
| `result` | <pre>NotificationContent\*</pre> | Notification content |
| `room_id` | <pre>string</pre> | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `timestamp` | <pre>long</pre> | Timestamp of the event, in Epoch-millis format |
| `n_type` | <pre>string</pre> | `user`: the notification type |
| `user` | <pre>string</pre> | `in`: a new user has subscribed to the same filters<br/>`out`: a user cancelled a shared subscription |
| `volatiles` | <pre>string</pre> | JSON String representing request [volatile data]({{ site_base_path }}api/1/essentials/volatile-data//) |

The `NotificationContent` class has the following properties for document notifications & messages:

| Property | Type |Description       |
|--------------------|------|------------------|
| `count` | <pre>int</pre> |  Updated users count sharing that same subscription |
