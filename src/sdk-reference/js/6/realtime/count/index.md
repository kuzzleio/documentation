---
layout: sdk.html.hbs
algolia: true
title: count
description: Count subscribers for a subscription room
---

# count

Returns the number of other connections sharing the same subscription.

## Signature

```javascript
count (roomId, [options])
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``roomId`` | string | Subscription room ID |
| ``options`` | object | Query options    |


### options

Additional query options

| Option     | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | boolean<br/>(`true`) | Make this request queuable or not |

## Resolve

Resolves to a number represensting active connections using the same provided subscription room.

## Usage

[snippet=count]
