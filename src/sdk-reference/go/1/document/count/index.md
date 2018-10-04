---
layout: sdk.html
algolia: true
title: count
description: Count documents
order: 200
---

# count

Given some filters, gets the number of matching documents from Kuzzle.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html) syntax.

## Signature

```go
Count(index string, collection string, body json.RawMessage, options types.QueryOptions) (int, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `body` | Object | The query to match |
| `options` | QueryOptions | An structure containing query options. |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |
| `IncludeTrash` | boolean | If set to `true`, includes the documents from the trash | `false`  |

## Return

## Usage

[snippet=count]
