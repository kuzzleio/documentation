---
layout: sdk.html.hbs
algolia: true
title: Offline First
order: 400
---


# Offline First

When using an unstable network connection, an application must maintain a normal behavior when it is disconnected. Our goal is to provide the right toolkit to handle such situations.


## Subscriptions

A subscription opens a permanent pipe between the client and Kuzzle. Whenever a real-time message or a modified document matches a subscription filter, a notification is sent by Kuzzle to the client (for instance, see the [Collection.subscribe]({{ site_base_path }}sdk-reference/collection/subscribe) method).

While in offline mode, the Kuzzle SDK client maintains all subscriptions configurations and, by default, when Kuzzle SDK client reconnects, all subscriptions are renewed. This behavior can be changed by setting the ``autoResubscribe`` to ``false``, in which case, each subscription will have to be renewed manually using the ``Room.renew`` method.


## Filtering Requests to be Queued

By default, when queuing is first activated, all requests are queued.

However, you can choose to omit certain request by using the [``queueFilter``]({{ site_base_path }}sdk-reference/kuzzle#properties) property. This property can be set to a function that accepts the request as an input value and returns a boolean result which indicates whether or not the request should be queud.

Additionally, almost all request methods accept a ``queuable`` option, which when set to ``false``, will cause the request to be discarded if the Kuzzle SDK is disconnected. This option overrides the ``queueFilter`` property.


## Taking Control of the Offline Queue

You can be notified about what's going on in the offline queue, by using the [`offlineQueuePush`]({{ site_base_path }}sdk-reference/essentials/events) and the [`offlineQueuePop`]({{ site_base_path }}sdk-reference/essentials/events) events.  

The `offlineQueuePush` event is fired whenever a request is queued. It will emit an object containing a `query` property, describing the queued request, and an optional `cb` property containing the corresponding callback, if any.

The `offlineQueuePop` event is fired whenever a request has been removed from the queue, either because the queue limits have been reached, or because the request has been replayed. It provides the removed request to its listeners.

The `offlineQueueLoader` property of the Kuzzle SDK instance loads requests to the queue, **before any previously queued request**. It is invoked every time the Kuzzle SDK starts dequeuing requests.  
This property must be set with a function that returns an array of objects with the following accessible properties:

* a `query` property, containing the request to be replayed
* an optional `cb` property pointing to the callback to invoke after the completion of the request

Finally, if the provided methods don't give you enough control over the offline queue, you can access and edit the queue directly using the ``offlineQueue`` property.

---

## Automatic Offline-Mode

You can set the ``offlineMode`` option to ``auto`` when instantiating the [Kuzzle SDK instance]({{ site_base_path }}sdk-reference/kuzzle). This sets the offline mode configuration to the following presets:

* ``autoReconnect`` = ``true``
* ``autoQueue`` = ``true``
* ``autoReplay`` = ``true``
* ``autoResubscribe`` = ``true``
