---
layout: sdk.html.hbs
algolia: true
title: mDelete
description:
order: 200
---

# mDelete

Deletes multiple documents.

Throws a partial error (error code 206) if one or more document deletions fail.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document indexation (indexed documents are available for `search`).

## Arguments

```java
io.kuzzle.sdk.StringVector mDelete(
  String index, 
  String collection, 
  io.kuzzle.sdk.StringVector ids, 
  io.kuzzle.sdk.QueryOptions options
)
io.kuzzle.sdk.StringVector mDelete(
  String index, 
  String collection, 
  io.kuzzle.sdk.StringVector ids
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `ids` | io.kuzzle.sdk.StringVector | The ids of the documents to delete |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`)| If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns an `io.kuzzle.sdk.StringVector` containing the list of the deleted document ids (more details about [StringVector]({{ site_base_path }}sdk-reference/java/1/essentials/stringvector))

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=m-delete]
