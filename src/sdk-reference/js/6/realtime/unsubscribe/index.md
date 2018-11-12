---
layout: sdk.html.hbs
algolia: true
title: unsubscribe
description: Removes a subscription
---

# unsubscribe

Removes a subscription.

## Arguments

```javascript
unsubscribe (roomId, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``roomId`` | string | Subscription room ID |
| ``options`` | object | Query options    |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | bool<br/>(`true`) | Make this request queuable or not |

## Usage

[snippet=unsubscribe]
