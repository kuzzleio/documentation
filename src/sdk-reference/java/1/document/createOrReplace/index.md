---
layout: sdk.html.hbs
algolia: true
title: createOrReplace
description:
order: 200
---

# createOrReplace

Creates a new document in the persistent data storage, or replace it if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```java
String createOrReplace(
  String index, 
  String collection, 
  String id, 
  String body, 
  io.kuzzle.sdk.QueryOptions options
)
String createOrReplace(
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
| `body` | <pre>String</pre> | A JSON String containing the body of the document |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

###### **Options**

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`)| Make this request queuable or not |
| `refresh` | <pre>String</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the updated/created document.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=create-or-replace]
