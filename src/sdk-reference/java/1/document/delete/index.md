---
layout: sdk.html.hbs
algolia: true
title: delete
description:
order: 200
---

# delete

Given a document id, deletes the corresponding document from Kuzzle.

Only documents in the persistent data storage layer can be deleted.

The optional parameter refresh can be used with the value wait_for in order to wait for the document to be deleted (and to no longer be available in search).


## Arguments

```java
String delete(
  String index, 
  String collection, 
  String id, 
  io.kuzzle.sdk.QueryOptions options
)
String delete(
  String index, 
  String collection, 
  String id
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `id` | <pre>String</pre> | The document id |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> |  The query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`)| Make this request queuable or not |
| `refresh` | <pre>String</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns the id of the deleted document.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=delete]
