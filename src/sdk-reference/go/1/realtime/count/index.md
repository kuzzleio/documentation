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

```go
func (r *Realtime) Count(roomID string, options types.QueryOptions) (int, error)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``roomId`` | string| Subscription room ID  |
| `options` | types.QueryOptions | Query options |

###### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Returns the number of active connections using the same provided subscription room.

## Usage

[snippet=count]
