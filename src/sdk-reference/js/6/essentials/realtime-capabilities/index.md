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

## Document notifications

When you want to subscribe to document notifications, you must define a collection, a set of Koncorde filters and a callback.  
The set of Koncorde filters defines a scope, the documents in your collection can be inside or outside this scope.  

Matching with the scope filters is performed during creation, modification or deletion operations.  
By default, a notification will be sent when a document enters the scope (`scope == 'in'`) and when a document leaves the scope (`scope == 'out'`).

If you want to receive notifications for each document created, modified or deleted, simply enter an empty set of filters.  

The callback passed as a parameter will be called with a `DocumentNotification` as only parameter for each notification sent by Kuzzle.

### DocumentNotification object

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `status` | Number | Status following [HTTP Standards](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) |
| `requestId` | String | Request unique identifier |
| `controller` | String | name of the controller that generated the notification |
| `action` | String | name of the action that generated the notification |
| `collection` | String | Collection name |
| `index` | String | Index name |
| `volatile` | object | Optional additional data sent with the request that generated the notification |
| `scope` | String | Indicates if the document enters or exits the subscription scope |
| `type` | String | Notification type (`document`) |
| `result` | object | The document that trigger the notification |
| `id` | String | Document unique identifier |


### Example 1: Simple subscription to document changes with default options

[snippet=document-notifications-default]

### Example 2: Subscription to documents leaving the scope

[snippet=document-notifications-leave-scope]
