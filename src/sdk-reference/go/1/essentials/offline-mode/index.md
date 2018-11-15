---
layout: sdk.html.hbs
algolia: true
title: Offline Mode
---

# Offline Mode

When using an unstable network connection, an application must maintain a normal behavior when it is disconnected. Our goal is to provide the right toolkit to handle such situations.

---

## Handling a Network Disconnect

There are two ways to handle a network disconnect:

* Automatically reconnect to Kuzzle when possible, and enter *offline mode* in the meantime. This is the default behavior.
* Stop all further communication with Kuzzle and invalidate the current instance and all its children. The application will have to manually reconnect once the network is available. To do so, simply set the `autoReconnect` option to `false` when creating the SDK instance.

*Offline mode* refers to the time between a `disconnected` and a `reconnected` event (see [Events]({{ site_base_path }}sdk-reference/go/1/essentials/events)).

---

## Subscriptions

A subscription opens a permanent pipe between the client and Kuzzle. Whenever a real-time message or a modified document matches a subscription filter, a [notification]{{ site_base_path }} is sent by Kuzzle to the client (for instance, see the [Realtime.Subscribe]({{ site_base_path }}sdk-reference/go/1/realtime/subscribe) method).

While in offline mode, the Kuzzle SDK client maintains all subscriptions configurations and, by default, when Kuzzle SDK client reconnects, all subscriptions are renewed. This behavior can be changed by setting the `autoResubscribe` to `false`.

---

## API Requests

While in offline mode, API requests can be queued, and then executed once the network connection has been reestablished.
By default, there is no request queuing.

* Queue all requests automatically when going offline by setting the `autoQueue` option to `true` (see [Kuzzle SDK constructor]({{ site_base_path }}sdk-reference/go/1/kuzzle/constructor))
* Start and stop queuing manually, by using the [Kuzzle.StartQueuing]({{ site_base_path }}sdk-reference/go/1/kuzzle/start-queuing) and [Kuzzle.StopQueuing]({{ site_base_path }}sdk-reference/go/1/kuzzle/stop-queuing) methods

The queue itself can be configured using the `queueTTL` and `queueMaxSize` options.

---

## Filtering Requests to be Queued

By default, when queuing is activated, all requests are queued.

However, almost all request methods accept a `queuable` option, which when set to `false`, will cause the request to be discarded if the Kuzzle SDK is disconnected.

---

## Handling Network Reconnect

<aside class="warning">
Setting <code>autoReplay</code> to <code>true</code> when using user authentication should generally be avoided.<br/><br/>
When leaving offline-mode, the JWT validity is verified. If it has expired, the token will be removed and a <code>tokenExpired</code> event will be triggered.<br/>
<br/>
If <code>autoReplay</code> is set, then all pending requests will be automatically played as an anonymous user.
</aside>

Once a `reconnected` event is fired, you may replay the content of the queue with the [Kuzzle.PlayQueue]({{ site_base_path }}sdk-reference/go/1/kuzzle/play-queue) method. Or you can let the Kuzzle SDK replay it automatically upon reconnection by setting the `autoReplay` option to `true`.

Requests are sent to Kuzzle with a `replayInterval` delay between each call.

Any request made while the client is processing the queue will be delayed until the queue is empty. This ensures that all requests are played in the right order.

---

## Taking Control of the Offline Queue

You can be notified about what's going on in the offline queue, by using the [`offlineQueuePush`]({{ site_base_path }}sdk-reference/go/1/essentials/events#offlinequeuepush-default) and the [`offlineQueuePop`]({{ site_base_path }}sdk-reference/go/1/essentials/events#offlinequeuepop-default) events.

The `offlineQueuePush` event is fired whenever a request is queued. It will emit an object containing a `types.QueryObject` describing the queued request.

The `offlineQueuePop` event is fired whenever a request has been removed from the queue, either because the queue limits have been reached, or because the request has been replayed. It also provides a `types.QueryObject` to its listeners.

The `offlineQueueLoader` property of the Kuzzle SDK instance loads requests to the queue, **before any previously queued request**. It is invoked every time the Kuzzle SDK starts dequeuing requests.

This property must be set with a `protocol.OfflineQueueLoader` which is an interface that require to implement a `Load` method that return an array of `types.QueryObject`:

```go
type OfflineQueueLoader interface {
	Load() []*types.QueryObject
}
```

The `offlineQueueLoader` can be used to send a login request before playing the offline queue.

---

## Automatic Offline-Mode

You can set the `offlineMode` option to `auto` when instantiating the [Kuzzle SDK instance]({{ site_base_path }}sdk-reference/go/1/kuzzle/constructor). This sets the offline mode configuration to the following presets:

* `autoReconnect` = `true`
* `autoQueue` = `true`
* `autoReplay` = `true`
* `autoResubscribe` = `true`
