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
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `from` | integer | Offset of the first document to fetch |
| `size` | integer | Maximum number of documents to retrieve per page  |
| `scroll` | std::string | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

## Body properties

### Optional:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

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
