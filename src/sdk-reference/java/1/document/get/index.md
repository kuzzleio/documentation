---
layout: sdk.html.hbs
algolia: true
title: get
description: Get a document from kuzzle
---


# get

Gets a document.

## Arguments

```java
String get(
  String index,
  String collection,
  String id,
  io.kuzzle.sdk.QueryOptions options
)
String get(
  String index,
  String collection,
  String id
)
```

<br/>

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | string | Collection name |
| `id` | <pre>String</pre> | Document ID |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| --- | --- | --- | ------- |
| `queuable` | <pre>boolean</pre><br/>(`true`)| If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a JSON string containing the document.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=get]
