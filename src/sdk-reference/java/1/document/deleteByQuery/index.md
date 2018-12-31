---
layout: sdk.html.hbs
title: deleteByQuery
description: Delete documents matching query
---

# deleteByQuery

Deletes documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Arguments

```java
io.kuzzle.sdk.StringVector deleteByQuery(
  String index,
  String collection,
  String query,
  io.kuzzle.sdk.QueryOptions options
)
io.kuzzle.sdk.StringVector deleteByQuery(
  String index,
  String collection,
  String query
)
```

<br/>

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `query` | <pre>String</pre> | A JSON string containing query to match |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`)| If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>String</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns an `io.kuzzle.sdk.StringVector` containing the list of the deleted document ids (more details about [StringVector]({{ site_base_path }}sdk-reference/java/1/java/1/essentials/stringvector))

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/error-handling).

## Usage

[snippet=delete-by-query]
