---
layout: sdk.html.hbs
title: renew
description: Room:renew
---
  

# renew
Renew the subscription. Force a new subscription using the same filters if no new ones are provided.

Unsubscribes first if this `Room` object was already listening to events.

---

## renew([filters], notificationCallback, subscriptionCallback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``filters`` | JSON Object | [Filters]({{ site_base_path }}koncorde/1) |
| ``notificationCallback`` | function | Function called each time a [notification]({{ site_base_path }}sdk-reference/android/3/essentials/notifications) is received |
| ``subscriptionCallback`` | function | Function called with the subscription result |

## Usage

[snippet=renew-1]
