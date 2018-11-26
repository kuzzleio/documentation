---
layout: sdk.html.hbs
algolia: true
title: Unsubscribe
description: Removes a subscription
algolia: true
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
| `roomId` | <pre>string</pre> | Subscription room ID  |
| `options` | <pre>types.QueryOptions</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | Make this request queuable or not |

## Return

Return an error is something was wrong.

## Usage

[snippet=unsubscribe]
