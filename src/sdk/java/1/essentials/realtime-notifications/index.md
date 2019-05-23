---
code: false
type: page
title: Realtime notifications
description: List of realtime notifications sent by Kuzzle
order: 100
---

# Notifications

The [Realtime.subscribe](/sdk/java/1/controllers/realtime/subscribe/) method takes a `io.kuzzle.sdk.NotificationListener` event listener (implements the `EventListener` interface).  
That listener is fed with a `io.kuzzle.sdk.NotificationResult` object, whose content depends on the type of notification.

Properties can be accessed with usual getters and setters.

## Document & messages

These `io.kuzzle.sdk.NotificationResult` represent [documents changes & messages](/core/1/api/essentials/notifications#documents-changes-messages-default).

| Property     | Type                              | Description                                                                                           |
| ------------ | --------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `action`     | String                            | API controller's action                                                                               |
| `collection` | String                            | Data collection                                                                                       |
| `controller` | String                            | API controller                                                                                        |
| `index`      | String                            | Data index                                                                                            |
| `protocol`   | String                            | Network protocol used to modify the document                                                          |
| `result`     | io.kuzzle.sdk.NotificationContent | Notification content                                                                                  |
| `roomId`     | String                            | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `scope`      | String                            | `in`: document enters (or stays) in the scope<br/>`out`: document leaves the scope                    |
| `timestamp`  | BigInteger                        | Timestamp of the event, in Epoch-millis format                                                        |
| `nType`      | String                            | `document`: the notification type                                                                     |
| `volatile`   | String                            | JSON String representing request [volatile data](/core/1/api/essentials/volatile-data/)               |

The `io.kuzzle.sdk.NotificationContent` object has the following properties for document notifications & messages:

| Property  | Type   | Description                                                                                           |
| --------- | ------ | ----------------------------------------------------------------------------------------------------- |
| `id`      | String | Document unique ID<br/>`null` if the notification is from a real-time message                         |
| `content` | String | A JSON String message or full document content. Not present if the event is about a document deletion |

## User

These `io.kuzzle.sdk.NotificationResult` represent [user events](/core/1/api/essentials/notifications#user-events-default).

| Property     | Type                              | Description                                                                                           |
| ------------ | --------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `action`     | String                            | API controller's action                                                                               |
| `collection` | String                            | Data collection                                                                                       |
| `controller` | String                            | API controller                                                                                        |
| `index`      | String                            | Data index                                                                                            |
| `protocol`   | String                            | Network protocol used by the entering/leaving user                                                    |
| `result`     | io.kuzzle.sdk.NotificationContent | Notification content                                                                                  |
| `roomId`     | String                            | Subscription channel identifier. Can be used to link a notification to its corresponding subscription |
| `timestamp`  | Number                            | Timestamp of the event, in Epoch-millis format                                                        |
| `nType`      | BigInteger                        | `user`: the notification type                                                                         |
| `user`       | String                            | `in`: a new user has subscribed to the same filters<br/>`out`: a user cancelled a shared subscription |
| `volatile`   | String                            | JSON String representing request [volatile data](/core/1/api/essentials/volatile-data/)               |

The `io.kuzzle.sdk.NotificationContent` object has the following properties for user events:

| Property | Type | Description                                        |
| -------- | ---- | -------------------------------------------------- |
| `count`  | int  | Updated users count sharing that same subscription |
