---
layout: sdk.html.hbs
algolia: true
title: count
description: Count documents
order: 200
---

# Count

Counts documents in a data collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the data collection.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.


## Arguments

```go
Count(
  index string, 
  collection string, 
  body json.RawMessage, 
  options types.QueryOptions
) (int, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>json.RawMessage</pre> | A JSON string containing the query to match |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options | 

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns the number of documents matching the given query.

## Usage

[snippet=count]
