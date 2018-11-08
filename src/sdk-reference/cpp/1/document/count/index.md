---
layout: sdk.html.hbs
algolia: true
title: count
description:
order: 200
---

# count

Counts documents in a data collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the data collection.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Arguments

```cpp
int count(
    const std::string& index, 
    const std::string& collection, 
    const std::string& body, 
    kuzzleio::query_options *options
)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>std::string</pre> | Index name |
| `collection` | <pre>std::string</pre> | Collection name |
| `body` | <pre>std::string</pre> | A JSON string containing the query to match |
| `options` | <pre>query_options</pre> | A pointer to a `query_options` |

### options

Additional query options

| Option   | Type (default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns the number of matched documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=count]
