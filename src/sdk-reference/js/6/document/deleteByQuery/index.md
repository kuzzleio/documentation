---
layout: sdk.html.hbs
algolia: true
title: deleteByQuery
description:
order: 200
---

# deleteByQuery

Deletes all the documents from Kuzzle that match the given filter or query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/query-dsl.html) syntax.

## Arguments

```javascript
deleteByQuery(index, collection, body = {}, options = {})
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `body` | Object | The query to match |
| `options` | Object | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolve

Resolves to an `Array` of strings containing the deleted document ids.

## Usage

[snippet=delete-by-query]
