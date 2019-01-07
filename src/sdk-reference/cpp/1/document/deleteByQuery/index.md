---
layout: sdk.html.hbs
title: deleteByQuery
description: Delete documents matching query
order: 200
---

# deleteByQuery

Deletes documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Signature

```cpp
std::vector<std::string> deleteByQuery(
    const std::string& index, 
    const std::string& collection, 
    const std::string& query);

std::vector<std::string> deleteByQuery(
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
| `query` | <pre>const std::string&</pre> | JSON string representing query to match |
| `options` | <pre>kuzzleio::query_options\*</pre> | Query options |

### options

Additional query options

| Options   | Type<br/>(default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>const std::string&<br/>(`""`)</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

A vector containing the ids of deleted documents.

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/cpp/1/error-handling).

## Usage

[snippet=delete-by-query]
