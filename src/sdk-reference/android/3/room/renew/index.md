---
layout: sdk.html.hbs
algolia: true
title: renew
description: Room:renew
---
  

# renew
[snippet=renew-1]
Renew the subscription. Force a new subscription using the same filters if no new ones are provided.

Unsubscribes first if this `Room` object was already listening to events.

---

## renew([filters], notificationCallback, subscriptionCallback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``filters`` | JSON Object | [Filters]({{ site_base_path }}kuzzle-dsl) |
| ``notificationCallback`` | function | Function called each time a [notification]({{ site_base_path }}sdk-reference/essentials/notifications) is received |
| ``subscriptionCallback`` | function | Function called with the subscription result |
