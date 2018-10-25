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

```cpp
void publish(
  const std::string& index,
  const std::string& collection,
  const std::string& message,
  kuzzleio::query_options *options=nullptr
);
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | const std::string& | Index name    |
| ``collection`` | const std::string& | Collection name    |
| ``message`` | const std::string& | JSON string representing the message to send |
| `options` | kuzzleio::query_options* | A pointer to a `query_options` containing query options |

### **options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Usage

[snippet=publish]
