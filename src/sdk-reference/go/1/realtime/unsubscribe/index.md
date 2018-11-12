---
layout: sdk.html.hbs
algolia: true
title: Unsubscribe
description: Removes a subscription
order: 200
---

# Unsubscribe

Removes a subscription.

## Arguments

```go
func (r *Realtime) Unsubscribe(roomID string, options types.QueryOptions) error
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `roomId` | string| Subscription room ID  |
| `options` | types.QueryOptions | Query options |

### options

Additional query options

| Option     | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | bool<br/>(`true`) | Make this request queuable or not |

## Return

Return an error is something was wrong.

## Usage

[snippet=unsubscribe]
