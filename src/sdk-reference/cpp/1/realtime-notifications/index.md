---
layout: sdk.html.hbs
title: Realtime notifications
description: List of realtime notifications sent by Kuzzle
order: 300
---
# Notifications

The [realtime:subscribe]({{ site_base_path }}sdk-reference/cpp/1/realtime/subscribe) method takes a listener of type `kuzzleio::NotificationListener`.  
That listener is called with a `const kuzzleio::notification_result*` argument, pointing to an object whose content depends on the type of notification received.

## Document & messages

These `kuzzleio::notification_result` represent [documents changes & messages]({{ site_base_path }}api/1/notifications/#documents-changes-messages-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | <pre>const char\*</pre> | API controller's action  |
| `collection` | <pre>const char\*</pre> | Collection name |
| `controller` | <pre>const char\*</pre> | API controller  |
| `index` | <pre>const char\*</pre> | Index name |
| `protocol` | <pre>const char\*</pre> | Network protocol used to modify the document |
| `result` | <pre>kuzzleio::notification_content\*</pre> | Notification content |
| `room_id` | <pre>const char\*</pre> | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `scope` | <pre>const char\*</pre> | `in`: document enters (or stays) in the scope<br/>`out`: document leaves the scope |
| `timestamp` | <pre>unsigned long long</pre> | Timestamp of the event, in Epoch-millis format |
| `n_type` | <pre>const char\*</pre> | `document`: the notification type |
| `volatiles` | <pre>const char\*</pre> | JSON String representing request [volatile data]({{ site_base_path }}api/1/essentials/volatile-data//) |

The `kuzzleio::notification_content` struct has the following properties for document notifications & messages:

| Property | Type |Description       |
|--------------------|------|------------------|
| `id` | <pre>const char\*</pre> | Document unique ID<br/>`null` if the notification is from a real-time message|
| `content` | <pre>const char\*</pre> | JSON String representing the message or the full document content. Not present if the event is about a document deletion |

## User

These `kuzzleio::notification_result` represent [user events]({{ site_base_path }}api/1/notifications/#user-events-default).

| Property | Type |Description       |
|--------------------|------|------------------|
| `action` | <pre>const char\*</pre> | API controller's action  |
| `collection` | <pre>const char\*</pre> | Collection name |
| `controller` | <pre>const char\*</pre> | API controller  |
| `index` | <pre>const char\*</pre> | Index name |
| `protocol` | <pre>const char\*</pre> | Network protocol used by the entering/leaving user |
| `result` | <pre>kuzzleio::notification_content\*</pre> | Notification content |
| `room_id` | <pre>const char\*</pre> | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `timestamp` | <pre>unsigned long long</pre> | Timestamp of the event, in Epoch-millis format |
| `n_type` | <pre>const char\*</pre> | `user`: the notification type |
| `user` | <pre>const char\*</pre> | `in`: a new user has subscribed to the same filters<br/>`out`: a user cancelled a shared subscription |
| `volatiles` | <pre>const char\*</pre> | JSON String representing request [volatile data]({{ site_base_path }}api/1/essentials/volatile-data//) |

The `kuzzleio::notification_content` struct has the following properties for document notifications & messages:

| Property | Type |Description       |
|--------------------|------|------------------|
| `count` | <pre>int</pre> |  Updated users count sharing that same subscription |
