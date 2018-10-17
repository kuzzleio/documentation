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

## Signature

```go
Search(index string, collection string, body json.RawMessage, options types.QueryOptions) (*types.SearchResult, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `body` | json.RawMessage | A JSON string containing the search query |
| `options` | types.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `from` | integer | Offset of the first document to fetch | `false` |
| `size` | integer | Maximum number of documents to retrieve per page  | `false` |
| `scroll` | std::string | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) | `false` |

## Return

Returns a `SearchResult` struct

### Properties

| Name | Type | Description |
| --- | --- | --- |
| Documents | string | A JSON string containing the retrieved documents |
| Fetched | int | The number of fetched documents |
| Total | int | The total number of documents matching the query |
| Aggregations | string | A JSON string containing the computed aggregations |
| Collection | string | The collection on which the search was performed |
| Filters | string | The original query |
| Options | types.QueryOptions | The original query options |

### Functions

| Name | Type | Description |
| --- | --- | --- |
| Next | `SearchResult` | Returns a new page of `size` next documents |

## Usage

[snippet=search]
