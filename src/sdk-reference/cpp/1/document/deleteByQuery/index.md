---
layout: sdk.html
algolia: true
title: deleteByQuery
description:
order: 200
---

# deleteByQuery

Deletes all the documents from Kuzzle that match the given filter or query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html) syntax.

## Signature

```cpp
std::vector<std::string> deleteByQuery(const std::string& index, const std::string& collection, const std::string& body, query_options *options=nullptr)
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | std::string | Index name | yes |
| `collection` | std::string | Collection name | yes |
| `body` | std::string | A JSON string containing query to match | yes |
| `options` | query_options | A pointer to a `query_options` containing query options | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns the list of the deleted document ids.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=delete-by-query]
