---
layout: sdk.html
algolia: true
title: deleteByQuery
description:
order: 200
---

# deleteByQuery

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {string} body
 * @param {object} options
 * @returns {}
 */
deleteByQuery(index, collection, body = {}, options = {})
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | String | Index name | yes |
| `collection` | String | Collection name | yes |
| `body` | Object | The query to match | yes |
| `options` | Object | An object containing query options. | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Resolve

Resolves to an `Array` of strings containing the deleted document ids.

## Usage

[snippet=delete-by-query]
