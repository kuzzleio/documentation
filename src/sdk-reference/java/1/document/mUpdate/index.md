---
layout: sdk.html.hbs
algolia: true
title: mUpdate
description:
order: 200
---

# mUpdate

Updates documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents can not be updated.

Conflicts may occur if the same document gets updated multiple times within a short time on a database cluster. When this happens, Kuzzle answers with an error that clients have to handle.  
You may set the `retryOnConflict` optional argument with a positive integer, asking Kuzzle to retry updating the document that number of times before rejecting the request with an error.

## Arguments

```java
String mUpdate(
  String index, 
  String collection, 
  String documents, 
  io.kuzzle.sdk.QueryOptions options
)
String mUpdate(
  String index, 
  String collection, 
  String documents
)
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `documents` | <pre>String</pre> | A JSON string containing the documents to update |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `queuable` | Boolean | Make this request queuable or not |
| `refresh` | <pre>String</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>int</pre> (`0`) | The number of times the database layer should retry in case of version conflict |

## Return

Returns a JSON string containing the updated documents.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=m-update]
