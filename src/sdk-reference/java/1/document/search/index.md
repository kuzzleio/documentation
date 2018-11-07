---
layout: sdk.html.hbs
algolia: true
title: search
description:
order: 200
---

# search

Only documents in the persistent data storage layer can be searched.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html) syntax.

An empty body matches all documents in the collection.

Optional arguments:

* `size` controls the maximum number of documents returned in the response. Cannot exceed 10000.
* `from` is usually used with the `size` argument, and defines the offset from the first result you want to fetch
* `scroll` allows to fetch large result sets, and it must be set with a [time duration](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units). If set, a forward-only cursor will be created (and automatically destroyed at the end of the set duration).
This cursor can then be moved forward using the `next` method of the returned `search_result` struct.

<div class="alert alert-info">
  <p>
  When processing a large number of documents (i.e. more than 1000), it is advised to paginate the results using <code>search_result.next</code> rather than increasing the size parameter.
  </p>
</div>

## Arguments

```java
io.kuzzle.sdk.SearchResult search(
  String index,
  String collection, 
  String body, 
  io.kuzzle.sdk.QueryOptions options
)
io.kuzzle.sdk.SearchResult search(
  String index,
  String collection, 
  String body
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>String</pre> | Index name |
| `collection` | <pre>String</pre> | Collection name |
| `body` | <pre>String</pre> | A JSON string containing the search query |
| `options` | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`)| If true, queues the request during downtime, until connected to Kuzzle again |
| `from` | int | Offset of the first document to fetch |
| `size` | int | Maximum number of documents to retrieve per page  |
| `scroll` | <pre>String</pre> | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

## Return

Returns a `io.kuzzle.sdk.SearchResult` object

### Properties

| Name | Type | Description |
| --- | --- | --- |
| getDocuments | <pre>String</pre> | A JSON string containing the retrieved documents |
| getFetched | int | The number of fetched documents |
| getTotal | int | The total number of documents matching the query |
| getAggregations | <pre>String</pre> | A JSON string containing the computed aggregations |
| getCollection | <pre>String</pre> | The collection on which the search was performed |
| getFilters | <pre>String</pre> | The original query |
| getOptions | <pre>io.kuzzle.sdk.QueryOptions</pre> | The original query options |
| next | function | Returns a new `io.kuzzle.sdk.SearchResult` of `size` next documents |

### Functions

| Name | Type | Description |
| --- | --- | --- |
| next | `io.kuzzle.sdk.SearchResult` | Returns a new page of `size` next documents |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/java/1/essentials/error-handling).

## Usage

[snippet=search]
