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

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} body
 * @param {object} options
 * @returns {Promise.<int>}
 */
count(index, collection, body, (options = null));
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `body` | Object | The query to match |
| `options` | Object | An object containing query options. |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `includeTrash` | boolean | If set to `true`, includes the documents from the trash | `false`  |

## Resolve

Resolves to the number of matched documents.

## Usage

[snippet=count]


