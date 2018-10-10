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

* `aggregations` details how to aggregate the search results. See the [Elasticsearch Documentation](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/search-aggregations.html) for
more details
* `size` controls the maximum number of documents returned in the response
* `from` is usually used with the `size` argument, and defines the offset from the first result you want to fetch
* `scroll` allows to fetch large result sets, and it must be set with a [time duration](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units). If set, a forward-only cursor will be created (and automatically destroyed at the end of the set duration), and its identifier will be returned in the `_scroll_id` property, along with the first
page of results. This cursor can then be moved forward using the [`scroll` API action]({{ site_base_path }}api-documentation/controller-document/scroll)

<div class="alert alert-info">
  <p>
  When processing a large number of documents (i.e. more than 1000), using search requests only are not the best option.
  </p>
  <p>
  See <a href="{{ site_base_path }}api-documentation/controller-document/scroll">scroll</a> for a more efficient way of processing large result sets.
  </p>
</div>

## Signature

```cpp
search_result* search(const std::string& index, const std::string& collection, const std::string& body, query_options *options=nullptr)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | std::string | Index name |
| `collection` | std::string | Collection name |
| `id` | std::string | The document id |
| `body` | std::string | A JSON string containing the body of the document |
| `options` | query_options | A pointer to a `query_options` containing query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=search]
