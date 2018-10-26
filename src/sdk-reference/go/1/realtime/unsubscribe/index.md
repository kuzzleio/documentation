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

```go
func (r *Realtime) Unsubscribe(roomID string, options types.QueryOptions) error
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

Return an error is something was wrong.

## Usage

[snippet=unsubscribe]
