---
layout: sdk.html.hbs
algolia: true
title: count
description: Count subscribers for a subscription room
order: 200
---

# count

Returns the number of other connections sharing the same subscription.

## Signature

```javascript
/**
 * @param {string} roomId
 * @param {object} [options]
 * @returns {Promise<number>}
 */
count (roomId, options = null)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``roomId`` | string | Subscription room ID |
| ``options`` | object | Query options    |


### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to the number of active connections using the same provided subscription room

## Usage

[snippet=count]
