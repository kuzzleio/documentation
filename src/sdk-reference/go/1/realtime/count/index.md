---
layout: sdk.html.hbs
algolia: true
title: Count
description: Count subscribers for a subscription room
---

# Count

Returns the number of other connections sharing the same subscription.

## Arguments

```go
func (r *Realtime) Count(roomID string, options types.QueryOptions) (int, error)
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

Returns the number of active connections using the same provided subscription room.

## Usage

[snippet=count]
