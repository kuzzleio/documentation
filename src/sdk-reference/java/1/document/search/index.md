---
layout: sdk.html.hbs
algolia: true
title: search
description:
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
