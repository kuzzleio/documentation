---
layout: sdk.html.hbs
algolia: true
title: mGet
description: Get multiple documents
algolia: true
---

# mGet

Gets multiple documents.

Throws a partial error (error code 206) if one or more document can not be retrieved.

## Arguments

```java
String mGet(
  String index,
  String collection,
  io.kuzzle.sdk.StringVector ids,
  io.kuzzle.sdk.QueryOptions options
)
String mGet(
  String index,
  String collection,
  io.kuzzle.sdk.StringVector ids
)
```

<br/>

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `ids` | io.kuzzle.sdk.StringVector | Document IDs |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`)| If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a JSON string containing the retrieved documents.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=m-get]
