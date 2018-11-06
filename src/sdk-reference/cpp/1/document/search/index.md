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

```cpp
search_result* search(
  const std::string& index, 
  const std::string& collection, 
  const std::string& body, 
  kuzzleio::query_options *options=nullptr
)
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>std::string</pre> | Index name |
| `collection` | <pre>std::string</pre> | Collection name |
| `body` | <pre>std::string</pre> | A JSON string containing the search query |
| `options` | <pre>query_options</pre> | A pointer to a `query_options` containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| ------ | -------------- | ----------- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `from` | integer | Offset of the first document to fetch |
| `size` | integer | Maximum number of documents to retrieve per page  |
| `scroll` | <pre>std::string</pre> | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

## Return

Returns a `SearchResult` object. See how to [handle search results hre]({{ site_base_path }}sdk-reference/cpp/1/esssentials/search-result).

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=search]
