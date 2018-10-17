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

```java
io.kuzzle.sdk.SearchResult search(java.lang.String, java.lang.String, java.lang.String, io.kuzzle.sdk.QueryOptions) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
io.kuzzle.sdk.SearchResult search(java.lang.String, java.lang.String, java.lang.String) throws io.kuzzle.sdk.BadRequestException, io.kuzzle.sdk.ForbiddenException, io.kuzzle.sdk.GatewayTimeoutException, io.kuzzle.sdk.InternalException, io.kuzzle.sdk.NotFoundException, io.kuzzle.sdk.PartialException, io.kuzzle.sdk.PreconditionException, io.kuzzle.sdk.ServiceUnavailableException, io.kuzzle.sdk.SizeLimitException, io.kuzzle.sdk.UnauthorizedException, io.kuzzle.sdk.KuzzleException;
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `body` | String | A JSON string containing the search query |
| `options` | io.kuzzle.sdk.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `from` | int | Offset of the first document to fetch | `false` |
| `size` | int | Maximum number of documents to retrieve per page  | `false` |
| `scroll` | String | When set, gets a forward-only cursor having its ttl set to the given value (ie `30s`; cf [elasticsearch time limits](https://www.elastic.co/guide/en/elasticsearch/reference/current/common-options.html#time-units)) | `false` |

## Return

Returns a `io.kuzzle.sdk.SearchResult` object

### Properties

| Name | Type | Description |
| --- | --- | --- |
| getDocuments | String | A JSON string containing the retrieved documents |
| getFetched | int | The number of fetched documents |
| getTotal | int | The total number of documents matching the query |
| getAggregations | String | A JSON string containing the computed aggregations |
| getCollection | String | The collection on which the search was performed |
| getFilters | String | The original query |
| getOptions | io.kuzzle.sdk.QueryOptions | The original query options |
| next | function | Returns a new `io.kuzzle.sdk.SearchResult` of `size` next documents |

### Functions

| Name | Type | Description |
| --- | --- | --- |
| next | `io.kuzzle.sdk.SearchResult` | Returns a new page of `size` next documents |

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=search]
