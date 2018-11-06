---
layout: sdk.html.hbs
algolia: true
title: count
description: Count documents
order: 200
---

# count

Given some filters, gets the number of matching documents from Kuzzle.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html) syntax.


## Arguments

```go
Count(
  index string, 
  collection string, 
  body json.RawMessage, 
  options types.QueryOptions
) (int, error)
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>json.RawMessage</pre> | A JSON string containing the query to match |
| `options` | <pre>types.QueryOptions</pre> | An struct containing query options | 

### options

Additional query options

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `Queuable` | <pre>bool</pre>  (`true`) | Make this request queuable or not |

## Return

Returns the number of documents matching the given query.

## Usage

[snippet=count]
