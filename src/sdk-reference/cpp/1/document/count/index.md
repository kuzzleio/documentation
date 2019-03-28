---
layout: sdk.html.hbs
title: count
description: Count documents matching the given query
---

# count

Counts documents in a collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the collection.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Signature

```cpp
int count(const std::string& index, const std::string& collection);

int count(
    const std::string& index, 
    const std::string& collection, 
    const kuzzleio::query_options& options);

int count(
    const std::string& index, 
    const std::string& collection, 
    const std::string& query);

int count(
    const std::string& index, 
    const std::string& collection, 
    const std::string& query, 
    const kuzzleio::query_options& options);
```

## Arguments

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>const std::string&</pre> | Index name |
| `collection` | <pre>const std::string&</pre> | Collection name |
| `query` | <pre>const std::string&</pre> | JSON string representing the query to match |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Option   | Type<br/>(default)    | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |

## Return

Returns the number of matched documents.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=count]
