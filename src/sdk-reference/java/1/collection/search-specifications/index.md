---
layout: sdk.html.hbs
title: searchSpecifications
description:
---

# searchSpecifications

{{{since "1.0.0"}}}

Searches collection specifications.

## Arguments

```java
  public io.kuzzle.sdk.SpecificationSearchResult searchSpecifications(
    String query,
    io.kuzzle.sdk.QueryOptions
  )
  public io.kuzzle.sdk.SpecificationSearchResult searchSpecifications(
    String query
  )
```

<br/>

| Argument | Type | Description |
| --- | --- | --- |
| `query` | <pre>String</pre> | A JSON string containing the query of the document |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

### Options

| Option     | Type (default) | Description                       |
| ---------- | -------------- | --------------------------------- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `from` | <pre>int</pre><br/>(`1`) | Offset of the first document to fetch |
| `size` | <pre>int</pre><br/>(`10`) | Maximum number of documents to retrieve per page  |
| `scroll` | <pre>String</pre><br/>(`""`) | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units)) |

## Query properties

### Optional:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.
- `aggregations`: controls how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a [io.kuzzle.sdk.SearchResult]({{ site_base_path }}src/sdk-reference/java/1/search-result) object.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=search-specifications]
