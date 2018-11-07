---
layout: sdk.html.hbs
algolia: true
title: get
description:
order: 200
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

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | string | Collection name |
| `id` | <pre>String</pre> | The document id |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- | ------- |
| `queuable` | <pre>boolean</pre> (`true`)| If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a JSON string containing the document.

| Name | Type | Description
| --- | --- | ---
| _source | object | The retrieved document

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=get]
