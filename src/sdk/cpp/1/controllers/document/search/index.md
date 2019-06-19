---
code: true
type: page
title: search
description: Search documents
---

# search

Searches documents.

There is a limit to how many documents can be returned by a single search query.
That limit is by default set at 10000 documents, and you can't get over it even with the from and size pagination options.

:::info
When processing a large number of documents (i.e. more than 1000), it is advised to paginate the results using [SearchResult.next](/sdk/cpp/1/core-classes/search-result/next/) rather than increasing the size parameter.
:::

## Arguments

```cpp
std::shared_ptr<kuzzleio::SearchResult> search(
    const std::string& index,
    const std::string& collection,
    const std::string& query);

std::shared_ptr<kuzzleio::SearchResult> search(
    const std::string& index,
    const std::string& collection,
    const std::string& query,
    const kuzzleio::query_options& options);
```

<br/>

| Argument     | Type                                 | Description                               |
| ------------ | ------------------------------------ | ----------------------------------------- |
| `index`      | <pre>const std::string&</pre>        | Index name                                |
| `collection` | <pre>const std::string&</pre>        | Collection name                           |
| `query`      | <pre>const std::string&</pre>        | JSON string representing the search query |
| `options`    | <pre>kuzzleio::query_options\*</pre> | Query options                             |

### options

Additional query options

| Option     | Type<br/>(default)                       | Description                                                                                                                                                                                                           |
| ---------- | ---------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`)             | If true, queues the request during downtime, until connected to Kuzzle again                                                                                                                                          |
| `from`     | <pre>int</pre><br/>(`0`)                 | Offset of the first document to fetch                                                                                                                                                                                 |
| `size`     | <pre>int</pre><br/>(`10`)                | Maximum number of documents to retrieve per page                                                                                                                                                                      |
| `scroll`   | <pre>const std::string&</pre><br/>(`""`) | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) |

### query

A JSON string representing the query. Query can have the following root properties:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a [kuzzleio::SearchResult](/sdk/cpp/1/core-classes/search-result) instance.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors](/sdk/cpp/1/essentials/error-handling).

## Usage

<<< ./snippets/search.cpp
