---
layout: sdk.html.hbs
title: searchSpecifications
description: Searches collection specifications
---

# searchSpecifications



Searches collection specifications.

There is a limit to how many items can be returned by a single search query.
That limit is by default set at 10000, and you can't get over it even with the from and size pagination options.

<div class="alert alert-info">
  When processing a large number of items (i.e. more than 1000), it is advised to paginate the results using <code>SearchResult.next</code> rather than increasing the size parameter.
</div>

## Signature

```csharp
public SearchResult searchSpecifications(string query);

public SearchResult searchSpecifications(string query, query_options options);

```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `query` | <pre>string</pre> | JSON string representing the query to match |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### query

A JSON string representing the Elasticsearch query.  
It may contain the following root properties:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.
- `aggregations`: controls how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-sort.html), in order of importance.

An empty query matches all documents in the queried collection.

### options

| Options    | Type (default) | Description                       |
| ---------- | -------------- | --------------------------------- |
| `queuable` | <pre>bool</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `from` | <pre>int</pre><br/>(`0`) | Offset of the first document to fetch |
| `size` | <pre>int</pre><br/>(`10`) | Maximum number of documents to retrieve per page  |
| `scroll` | <pre>string</pre><br/>(`""`) | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units)) |

## Return

Returns a [Kuzzleio::SearchResult]({{ site_base_path }}sdk-reference/csharp/1/search-result).

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=search-specifications]