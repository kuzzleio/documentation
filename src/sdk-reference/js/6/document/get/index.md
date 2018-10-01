---
layout: sdk.html
algolia: true
title: get
description:
order: 200
---

# get

Given a document id, retrieves the corresponding document from the database.

Only documents in the persistent data storage layer can be retrieved.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} options
 * @returns {Promise.<object>}
 */
get (index, collection, _id, options = {})
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | string | Index name | yes |
| `collection` | string | Collection name | yes |
| `id` | string | The document id | yes |
| `options` | Object | An object containing query options. | no |

###### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

## Usage

[snippet=get]
