---
layout: sdk.html.hbs
algolia: true
title: get
description:
order: 200
---

# get

Given a document id, retrieves the corresponding document from the database.

Only documents in the persistent data storage layer can be retrieved.

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
| `queuable` | <pre>boolean</pre> (`true`)| Make this request queuable or not |

## Return

Returns a JSON string containing the document.

| Name | Type | Description
| --- | --- | ---
| _source | object | The retrieved document

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=get]
