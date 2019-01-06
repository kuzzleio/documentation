---
layout: sdk.html.hbs
title: deleteByQuery
description: Delete documents matching query
order: 200
---

# deleteByQuery

Deletes documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t deleteByQuery(
    string index, 
    string collection, 
    string query);

public SWIGTYPE_p_std__vectorT_std__string_t deleteByQuery(
    string index, 
    string collection, 
    string query, 
    query_options options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `query` | <pre>string</pre> | JSON string representing query to match |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Options   | Type<br/>(default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A vector containing the ids of deleted documents.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=delete-by-query]
