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

```javascript
search (index, collection, [body], [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>string</pre> | A JSON string containing the search query |
| `options` | <pre>object</pre> | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `from` | integer | Offset of the first document to fetch |
| `size` | integer | Maximum number of documents to retrieve per page  |
| `scroll` | std::string | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

## Resolve

Resolves to a `SearchResult` object


### Properties

| Name | Type | Description |
| --- | --- | --- |
| documents | <pre>object</pre> | The retrieved documents |
| fetched | int | The number of fetched documents |
| total | int | The total number of documents matching the query |
| aggregations | <pre>object</pre> | The computed aggregations |
| collection | <pre>string</pre> | The collection on which the search was performed |
| filters | <pre>object</pre> | The original query |
| options | <pre>object</pre> | The original query options |

### Functions

| Name | Type | Description |
| --- | --- | --- |
| next | `search_result` | Returns a new page of `size` next documents |

## Usage

[snippet=search]
