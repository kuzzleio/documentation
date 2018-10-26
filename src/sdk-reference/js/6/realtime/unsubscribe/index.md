---
layout: sdk.html.hbs
algolia: true
title: unsubscribe
description: Removes a subscription
order: 200
---

# unsubscribe

Removes a subscription.

## Signature

```javascript
/**
 * @param {string} roomId
 * @param {object} [options]
 * @returns {Promise<>}
 */
unsubscribe (roomId, options = null)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``roomId`` | string | Subscription room ID |
| ``options`` | object | Query options    |

###### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Usage

[snippet=unsubscribe]
