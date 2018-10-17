---
layout: sdk.html.hbs
algolia: true
title: Realtime notifications
description: List of realtime notifications sent by Kuzzle
order: 100
---
# Notifications

The [Realtime.subscribe]({{ site_base_path }}sdk-reference/cpp/1/realtime/subscribe) method takes a callback of type `kuzzleio::NotificationListener`.  
That callback is called with a `kuzzleio::notification_result*` argument, pointing to an object whose content depends on the type of notification received.

# Document notifications & messages

These notifications represent [documents changes & messages]({{ site_base_path }}api/1/notifications/#documents-changes-messages-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | const char* | API controller's action  |
| `collection` | const char* | Data collection |
| `controller` | const char* | API controller  |
| `index` | const char* | Data index |
| `protocol` | const char* | Network protocol used to modify the document |
| `result` | kuzzleio::notification_content* | Notification content |
| `room_id` | const char* | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `scope` | const char* | `in`: document enters (or stays) in the scope<br/>`out`: document leaves the scope |
| `timestamp` | unsigned long long | Timestamp of the event, in Epoch-millis format |
| `n_type` | const char* | `document`: the notification type |
| `volatiles` | const char* | JSON String representing request [volatile data]({{ site_base_path }}api/1/volatile-data/) |

The `result` argument points to the following structure for document notifications & messages:

| Property | Type |Description       |
|--------------------|------|------------------|
| `id` | const char* | Document unique ID<br/>`null` if the notification is from a real-time message|
| `content` | const char* | A JSON String message or full document content. Not present if the event is about a document deletion |

# User notifications

These notifications represent [user events]({{ site_base_path }}api/1/notifications/#user-events-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | const char* | API controller's action  |
| `collection` | const char* | Data collection |
| `controller` | const char* | API controller  |
| `index` | const char* | Data index |
| `protocol` | const char* | Network protocol used by the entering/leaving user |
| `result` | kuzzleio::notification_content | Notification content |
| `room_id` | const char* | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `timestamp` | unsigned long long | Timestamp of the event, in Epoch-millis format |
| `n_type` | const char* | `user`: the notification type |
| `user` | const char* | `in`: a new user has subscribed to the same filters<br/>`out`: a user cancelled a shared subscription |
| `volatiles` | const char* | JSON String representing request [volatile data]({{ site_base_path }}api/1/volatile-data/) |

The `result` argument points to the following structure for user events:

| Property | Type |Description       |
|--------------------|------|------------------|
| `count` | int |  Updated users count sharing that same subscription |
