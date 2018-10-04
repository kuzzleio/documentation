---
layout: sdk.html.hbs
algolia: true
title: mGet
description:
order: 200
---

# mGet

Given `document ids`, retrieves the corresponding documents from the database.

Only documents in the persistent data storage layer can be retrieved.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more document can not be retrieved.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {Array.<string>} ids
 * @param {object} options
 * @returns {Promise.<Array>}
 */
mGet (index, collection, ids, options = {})
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `ids` | Array | The document ids |
| `options` | object | An object containing query options. |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `includeTrash` | boolean | If set to `true`, includes the documents from the trash | `false`  |

## Resolve

Resolves to an array of retrieved documents.

## Usage

[snippet=m-get]
