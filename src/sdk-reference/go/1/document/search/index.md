---
layout: sdk.html.hbs
algolia: true
title: search
description:
order: 200
---

# Search

Searches documents.

There is a limit to how many documents can be returned by a single search query.
That limit is by default set at 10000 documents, and you can't get over it even with the from and size pagination options.

<div class="alert alert-info">
  <p>
  When processing a large number of documents (i.e. more than 1000), it is advised to paginate the results using <code>SearchResult.next</code> rather than increasing the size parameter.
  </p>
</div>

## Arguments

```go
Search(
  index string, 
  collection string, 
  body json.RawMessage, 
  options types.QueryOptions
) (*types.SearchResult, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>json.RawMessage</pre> | A JSON string containing the search query |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `From` | integer | Offset of the first document to fetch |
| `Size` | integer | Maximum number of documents to retrieve per page  |
| `Scroll` | <pre>string</pre> | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

## Body properties

### Optional:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a `SearchResult` struct

### Properties

| Name | Type | Description |
| --- | --- | --- |
| Documents | <pre>string</pre> | A JSON string containing the retrieved documents |
| Fetched | int | The number of fetched documents |
| Total | int | The total number of documents matching the query |
| Aggregations | <pre>string</pre> | A JSON string containing the computed aggregations |
| Collection | <pre>string</pre> | The collection on which the search was performed |
| Filters | <pre>string</pre> | The original query |
| Options | <pre>types.QueryOptions</pre> | The original query options |

### Functions

| Name | Type | Description |
| --- | --- | --- |
| Next | `SearchResult` | Returns a new page of `size` next documents |

## Usage

[snippet=search]
