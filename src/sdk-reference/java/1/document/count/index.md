---
layout: sdk.html.hbs
algolia: true
title: count
description: Count documents
order: 200
---

# count

Given some filters, gets the number of matching documents from Kuzzle.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html) syntax.

## Arguments

```java
int count(
  String index, 
  String collection, 
  String body, 
  io.kuzzle.sdk.QueryOptions options
)
int count(
  String index, 
  String collection, 
  String body
)
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `body` | <pre>String</pre> | A JSON string representing the query to match |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options. |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |

## Return

Returns the number of documents matching the given query.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=count]
