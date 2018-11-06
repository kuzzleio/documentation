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

* `size` controls the maximum number of documents returned in the response. Cannot exceed 10000;
* `from` is usually used with the `size` argument, and defines the offset from the first result you want to fetch
* `scroll` allows to fetch large result sets, and it must be set with a [time duration](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units). If set, a forward-only cursor will be created (and automatically destroyed at the end of the set duration).
This cursor can then be moved forward using the `next` method of the returned `SearchResult` struct.

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

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>json.RawMessage</pre> | A JSON string containing the search query |
| `options` | <pre>types.QueryOptions</pre> | An struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | Make this request queuable or not |
| `From` | integer | Offset of the first document to fetch |
| `Size` | integer | Maximum number of documents to retrieve per page  |
| `Scroll` | <pre>string</pre> | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

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
