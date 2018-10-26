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

```cpp
int count(const std::string& room_id, kuzzleio::query_options *options=nullptr)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``room_id`` | const std::string& | Subscription room ID |
| `options` | kuzzleio::query_options* | A pointer to a `query_options` containing query options |

### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns the number of active connections using the same provided subscription room.

## Usage

[snippet=count]
