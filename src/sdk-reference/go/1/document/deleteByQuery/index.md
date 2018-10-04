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

```go
DeleteByQuery(index string, collection string, body string, options types.QueryOptions) ([]string, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `body` | string | A JSON string containing query to match |
| `options` | types.QueryOptions | Query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |
| `Refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns the list of the deleted document ids.

## Usage

[snippet=delete-by-query]
