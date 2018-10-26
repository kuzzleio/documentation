---
layout: sdk.html.hbs
algolia: true
title: publish
description:
order: 200
---

# publish

Sends a real-time `<message>` to Kuzzle. The `<message>` will be dispatched to all clients with subscriptions matching the `<index>`, the `<collection>` and the `<message>` content.  

The `<index>` and `<collection>` are indicative and serve only to distinguish the rooms. They are not required to be persisted in the database.

**Note:** real-time messages are not persisted in the database.

## Signature

```go
func (r *Realtime) Publish(
  index string,
  collection string,
  message json.RawMessage,
  options types.QueryOptions
) error
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``message`` | json.RawMessage | Message to send |
| ``options`` | types.QueryOptions | Query options    |

### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error is something was wrong.

## Usage

[snippet=publish]
