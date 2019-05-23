---
code: true
type: page
title: count
description: Count documents matching the given query
---

# count

Counts documents in a data collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the data collection.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Arguments

```java
int count(
  String index,
  String collection,
  String query,
  io.kuzzle.sdk.QueryOptions options
)
int count(
  String index,
  String collection,
  String query
)
```

<br/>

| Argument     | Type                                  | Description                                   |
| ------------ | ------------------------------------- | --------------------------------------------- |
| `index`      | <pre>String</pre>                     | Index name                                    |
| `collection` | <pre>String</pre>                     | Collection name                               |
| `query`      | <pre>String</pre>                     | A JSON string representing the query to match |
| `options`    | <pre>io.kuzzle.sdk.QueryOptions</pre> | The query options.                            |

### options

Additional query options

| Option     | Type<br/>(default)              | Description                                                                  |
| ---------- | ------------------------------- | ---------------------------------------------------------------------------- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns the number of documents matching the given query.

## Exceptions

Throws a `io.kuzzle.sdk.KuzzleException` if there is an error. See how to [handle errors](/sdk/java/1/essentials/error-handling/).

## Usage

<<< ./snippets/count.java
