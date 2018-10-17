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

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} body
 * @param {object} options
 * @returns {Promise.<object>}
 */
search (index, collection, body = {}, options = {})
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `body` | string | A JSON string containing the search query |
| `options` | object | An object containing query options. |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `from` | integer | Offset of the first document to fetch | `false` |
| `size` | integer | Maximum number of documents to retrieve per page  | `false` |
| `scroll` | std::string | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) | `false` |

## Resolve

Resolves to a `SearchResult` object


### Properties

| Name | Type | Description |
| --- | --- | --- |
| documents | object | The retrieved documents |
| fetched | int | The number of fetched documents |
| total | int | The total number of documents matching the query |
| aggregations | object | The computed aggregations |
| collection | string | The collection on which the search was performed |
| filters | object | The original query |
| options | object | The original query options |

### Functions

| Name | Type | Description |
| --- | --- | --- |
| next | `search_result` | Returns a new page of `size` next documents |

## Usage

[snippet=search]
