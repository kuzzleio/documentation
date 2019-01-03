---
layout: sdk.html.hbs
title: mCreate
description: Create multiple documents in kuzzle
---

# mCreate

Creates multiple documents.

Throws a partial error (error code 206) if one or more documents creations fail.

## Arguments

```java
String mCreate(
  String index,
  String collection,
  String documents,
  io.kuzzle.sdk.QueryOptions options
)
String mCreate(
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
| `documents` | <pre>String</pre> | A JSON string containing the documents to create |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`)| If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>String</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns an JSON string containing the created documents.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=m-create]
