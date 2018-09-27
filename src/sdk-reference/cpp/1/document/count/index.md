---
layout: sdk.html
algolia: true
title: count
description:
order: 200
---

# count

Given some filters, gets the number of matching documents from Kuzzle.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html) syntax.

## Signature

```cpp
int count(const std::string& index, const std::string& collection, const std::string& body, kuzzleio::query_options *options)
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | std::string | Index name | yes |
| `collection` | std::string | Collection name | yes |
| `body` | std::string | A JSON string containing the query to match | yes |
| `options` | query_options | A pointer to a `query_options` | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `includeTrash` | boolean | If set to `true`, includes the documents from the trash | `false`  |

## Return

Returns the number of matched documents.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=count]
