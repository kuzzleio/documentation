---
layout: sdk.html.hbs
algolia: true
title: mUpdate
description: Update documents
algolia: true
---

# mUpdate

Updates multiple documents.

Returns a partial error (error code 206) if one or more documents can not be updated.

Conflicts may occur if the same document gets updated multiple times within a short timespan in a database cluster.

You can set the `retryOnConflict` optional argument (with a retry count), to tell Kuzzle to retry the failing updates the specified amount of times before rejecting the request with an error.

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

<br/>

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `documents` | <pre>String</pre> | A JSON string containing the documents to update |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre>(`true) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>String</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>int</pre> (`0`) | The number of times the database layer should retry in case of version conflict |

## Return

Returns a JSON string containing the updated documents.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=m-update]
