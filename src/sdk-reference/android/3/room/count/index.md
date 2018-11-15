---
layout: sdk.html.hbs
algolia: true
title: count
description: Room:count
---
  

# count
Returns the number of subscribers in the room.

---

## count(callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``callback`` | function | Callback handling the response |

---

## Callback Response

Returns an `integer` containing the number of users subscribing to this room.

## Usage

[snippet=count-1]
> Callback response

```json
1
```