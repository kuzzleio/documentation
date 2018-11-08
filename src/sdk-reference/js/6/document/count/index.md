---
layout: sdk.html.hbs
algolia: true
title: count
description: Count documents
order: 200
---

# count

Counts documents in a data collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the data collection.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Arguments

```javascript
count(index, collection, body, [options]);
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>object</pre> | The query to match |
| `options` | <pre>object</pre> | An object containing query options. |

### options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

Resolves to the number of matched documents.

## Usage

[snippet=count]


