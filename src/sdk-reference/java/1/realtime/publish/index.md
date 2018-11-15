---
layout: sdk.html.hbs
algolia: true
title: publish
description: Publish a real-time message
---

# publish

Sends a real-time `<message>` to Kuzzle. The `<message>` will be dispatched to all clients with subscriptions matching the `<index>`, the `<collection>` and the `<message>` content.

The `<index>` and `<collection>` are indicative and serve only to distinguish the rooms. They are not required to exist in the database

**Note:** real-time messages are not persisted in the database.

## Arguments

```java
public void publish(String index, String collection, String message)
public void publish(
  String index,
  String collection,
  String message,
  io.kuzzle.sdk.QueryOptions options
)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| `index` | <pre>String</pre> | Index name    |
| `collection` | <pre>String</pre> | Collection name   |
| `message` | <pre>String</pre> | JSON string representing the message to send |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | Make this request queuable or not |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=publish]
