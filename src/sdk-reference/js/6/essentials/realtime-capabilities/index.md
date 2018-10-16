---
layout: sdk.html.hbs
algolia: true
title: Realtime capabilities
description: "Kuzzle realtime capabilities: Document notifications and Pub/Sub system"
order: 100
---

# Realtime capabilities

Kuzzle allows you to make real time with a room system a little different than traditional pub/sub system.  

With Kuzzle, a room is identified by a set of Koncorde filters on a particular collection.  
Users subscribing to the same filters on the same collection will therefore be in the same room.  

This system offers the possibility to receive notifications when creating, modifying or deleting documents and also to create a traditional pub/sub between several consumers.

# Document notifications

When you want to subscribe to document notifications, you must define a collection, a set of Koncorde filters and a callback. (See [realtime.subscribe]({{ site_base_path}}sdk-reference/js/6/realtime/subscribe))
The set of Koncorde filters defines a scope, the documents in your collection can be inside or outside this scope.  

Matching with the scope filters is performed during creation, modification or deletion operations.  
By default, a notification will be sent when a document enters the scope (`scope == 'in'`) and when a document leaves the scope (`scope == 'out'`).

If you want to receive notifications for each document created, modified or deleted, simply enter an empty set of filters.  

The callback passed as a parameter will be called with a [DocumentNotification]({{ site_base_path }}sdk-reference/js/6/essentials/realtime-capabilities/#documentnotification-object-default) as only parameter for each notification sent by Kuzzle.

## DocumentNotification object

These notifications can be sent on 4 different actions:
 - [Document creation]({{ site_base_path }}api-documentation/notifications/document-created/)
 - [Document deletion]({{ site_base_path }}api-documentation/notifications/document-deleted/)
 - [Document entering scope]({{ site_base_path }}api-documentation/notifications/document-scope-in/)
 - [Document exiting scope]({{ site_base_path }}api-documentation/notifications/document-scope-out/)

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `status` | Number | Status following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) |
| `requestId` | String | Request unique identifier |
| `controller` | String | Controller that generated the notification |
| `action` | String | Action that generated the notification (`create`, `delete` or `update`)|
| `collection` | String | Collection name |
| `index` | String | Index name |
| `volatile` | object | Optional additional data sent with the request that generated the notification |
| `scope` | String | Indicates if the document enters or exits the subscription scope |
| `result` | object | Depends on the action that generated the notification |
| `type` | String | Notification type (`document`) |


## Examples

### Simple subscription to document changes with default options

[snippet=document-notifications-default]

### Subscription to documents leaving the scope

[snippet=document-notifications-leave-scope]

# User notifications

When you subscribe to a set of Koncorde filters, you can also receive notifications when new users subscribe or unsubscribe to the same set of Koncorde filters as you.  

To do this, you must specify the `users` option of the [realtime.subscribe]({{ site_base_path}}sdk-reference/js/6/realtime/subscribe) at one of the following values:

 - `in`: receive a notification when an user enter the room
 - `out`: receive a notification when an user leave the room
 - `all`: combination of `in` and `out`

The callback passed as a parameter will be called with a [UserNotification]({{ site_base_path }}sdk-reference/js/6/essentials/realtime-capabilities/#documentnotification-object-default) for each notification sent by Kuzzle.

## UserNotification object

These notifications can be sent on 2 different actions:
 - [User subscribes]({{ site_base_path }}api-documentation/notifications/user-scope-in/)
 - [User leaves]({{ site_base_path }}api-documentation/notifications/user-scope-out/)

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `status` | Number | Status following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) |
| `requestId` | String | Request unique identifier |
| `controller` | String | name of the controller that generated the notification |
| `action` | String | name of the action that generated the notification |
| `collection` | String | Collection name |
| `index` | String | Index name |
| `volatile` | object | Optional additional data sent with the request that generated the notification |
| `result` | object | Contain the `count` of users in that room  |
| `type` | String | Notification type (`user`) |

## Example

[snippet=user-notifications]

# Pub / Sub system

It is also possible to use the realtime features of Kuzzle in Pub / Sub mode.  

In this mode, a client can subscribe to a room in the same way as for standard notifications with [realtime.subscribe]({{ site_base_path }}sdk-reference/js/6/realtime/subscribe) except that it is possible to specify arbitrary collections and set of Koncorde filters.  
These arbitrary collections are only used for room management and are not persistent in the database.  

It is then possible to send messages to these rooms using the [realtime.publish]({{ site_base_path }}sdk-reference/js/6/realtime/publish) method.  

As with [document notifications]({{ site_base_path  }}sdk-reference/js/6/essentials/realtime-capabilities/#document-notifications-default), it is possible to subscribe to users entering and leaving the room.

## Example

[snippet=pub-sub]
