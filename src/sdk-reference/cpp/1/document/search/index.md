---
layout: sdk.html.hbs
title: search
description: Search documents
order: 200
---

# search

Searches documents.

There is a limit to how many documents can be returned by a single search query.
That limit is by default set at 10000 documents, and you can't get over it even with the from and size pagination options.

<div class="alert alert-info">
  <p>
  When processing a large number of documents (i.e. more than 1000), it is advised to paginate the results using <code>SearchResult.next</code> rather than increasing the size parameter.
  </p>
</div>


## Signature

```cpp
SearchResult* search(
  const std::string& index,
  const std::string& collection,
  const std::string& query,
  kuzzleio::query_options *options=nullptr)
```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `query` | <pre>const std::string&</pre> | JSON string representing the search query |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option | Type<br/>(default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `from` | <pre>int</pre><br/>(`0`) | Offset of the first document to fetch |
| `size` | <pre>int</pre><br/>(`10`) | Maximum number of documents to retrieve per page  |
| `scroll` | <pre>const std::string&</pre><br/>(`""`) | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

### query

A JSON string representing the query. Query can have the following root properties:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a [kuzzleio::SearchResult]({{ site_base_path }}src/sdk-reference/cpp/1/search-result).

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=search]
