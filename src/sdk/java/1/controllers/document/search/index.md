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

<br/>

| Argument     | Type                                  | Description                               |
| ------------ | ------------------------------------- | ----------------------------------------- |
| `index`      | <pre>String</pre>                     | Index name                                |
| `collection` | <pre>String</pre>                     | Collection name                           |
| `body`       | <pre>String</pre>                     | A JSON string containing the search query |
| `options`    | <pre>io.kuzzle.sdk.QueryOptions</pre> | Query options                             |

### options

Additional query options

| Option     | Type<br/>(default)              | Description                                                                                                                                                                                                       |
| ---------- | ------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again                                                                                                                                      |
| `from`     | <pre>int</pre><br/>(`1`)        | Offset of the first document to fetch                                                                                                                                                                             |
| `size`     | <pre>int</pre><br/>(`10`)       | Maximum number of documents to retrieve per page                                                                                                                                                                  |
| `scroll`   | <pre>String</pre><br/>(`""`)    | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/common-options.html#time-units)) |

## Body properties

### Optional:

- `query`: the search query itself, using the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.
- `aggregations`: control how the search results should be [aggregated](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-aggregations.html)
- `sort`: contains a list of fields, used to [sort search results](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/search-request-sort.html), in order of importance.

An empty body matches all documents in the queried collection.

## Return

Returns a [io.kuzzle.sdk.SearchResult](/sdk/java/1/core-classes/search-result) object.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/search.java
