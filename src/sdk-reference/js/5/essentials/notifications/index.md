---
layout: full.html.hbs
algolia: true
title: Notifications
order: 300
---

# Notifications

With Kuzzle, you don't [subscribe]({{ site_base_path }}sdk-reference/collection/subscribe) to a room or a topic but, instead, you subscribe to documents.  
This means, that when you want to subscribe you must provide a set of filter definitions, using [Koncorde]({{ site_base_path }}kuzzle-dsl), that tell Kuzzle what documents should trigger a notification. Then, any time a document matches the defined filters, Kuzzle will send a notification to the subscriber.

You can also provide an empty set of filters, which will tell Kuzzle that you want to listen to any change occurring on a data collection, emulating the behavior of a traditional topic.

To subscribe, you must provide a callback that will be called each time a new notification is received.

Once you have subscribed, depending on the subscription configuration you provided, you may receive a notification when:

* a pub/sub message matches your criteria (real-time)
* a matching document is about to be created or deleted in real-time (deactivated by default) {{{deprecated "1.5.0"}}}
* a matching document is created, updated or deleted (once the change is effective in the database)
* a user enters or leaves the room (deactivated by default)

You may subscribe multiple times to the same room, with identical or different subscription parameters, and with different callbacks. This allows you to dispatch notifications to dedicated processes of your application, instead of maintaining a single all-purpose notification consumer (but you can do that too I you want).

---

[snippet=notifications-1]

[snippet=notifications-2]

[snippet=notifications-3]

## Document Notification

| Notification field | Type |Description       | Possible values |
|--------------------|------|------------------|-----------------|
| `document` | [Document]({{ site_base_path }}sdk-reference/document/) | Content of the document or real-time message that generated the notification | |
| `scope` | string | Indicates if the document enters or exits the subscription scope | `in`, `out` |
| `state` | string | {{{deprecated "1.5.0"}}} Shows if the document is about to be changed, or if the change is done | `pending`, `done` |
| `type` | string | Notification type | `document` |

#### Example

[snippet=notifications-4]

---

## User notification

| Notification field | Type |Description       | Possible values |
|--------------------|------|------------------|-----------------|
| `user` | string | Indicates if the user enters or leaves the subscribed room | `in`, `out` |
| `volatile` | JSON object | If provided during subscription, contains application specific information | |
| `result.count` | integer | Updated number of users subscribing to this room | |
| `type` | string | Notification type | `user` |

#### Example

[snippet=notifications-5]
