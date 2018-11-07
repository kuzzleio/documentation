---
layout: sdk.html.hbs
algolia: true
title: replace
description:
order: 200
---

# replace

Replaces an existing document in the persistent data storage.
Only documents in the persistent data storage layer can be replaced.

## Arguments

```java
String replace(
  String index, 
  String collection, 
  String id, 
  String body, 
  io.kuzzle.sdk.QueryOptions options
)
String replace(
  String index, 
  String collection, 
  String id, 
  String body
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `id` | <pre>String</pre> | The document id |
| `body` | <pre>String</pre> | A JSON string containing the body of the document |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `refresh` | <pre>String</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the updated document.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=replace]
