---
layout: sdk.html.hbs
algolia: true
title: publish
description: Publish a real-time message
algolia: true
---

# publish

Sends a real-time `<message>` to Kuzzle. The `<message>` will be dispatched to all clients with subscriptions matching the `<index>`, the `<collection>` and the `<message>` content.

The `<index>` and `<collection>` are indicative and serve only to distinguish the rooms. They are not required to exist in the database

**Note:** real-time messages are not persisted in the database.

## Arguments

```cpp
void publish(
  const std::string& index,
  const std::string& collection,
  const std::string& message,
  kuzzleio::query_options *options=nullptr
);
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>const std::string&</pre> | Index name    |
| `collection` | <pre>const std::string&</pre> | Collection name    |
| `message` | <pre>const std::string&</pre> | JSON string representing the message to send |
| `options` | <pre>kuzzleio::query_options*</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option     | Type<br/>(default)  | Description   |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | Make this request queuable or not |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=publish]
