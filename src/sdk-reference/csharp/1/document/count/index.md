---
layout: sdk.html.hbs
title: count
description: Count documents matching the given query
---

# count

Counts documents in a data collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the data collection.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Signature

```csharp
public int count(string index, string collection);

public int count(string index, string collection, query_options options);

public int count(string index, string collection, string query);

public int count(
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
| `query` | <pre>string</pre> | JSON string representing the query to match |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns the number of matched documents.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=count]
