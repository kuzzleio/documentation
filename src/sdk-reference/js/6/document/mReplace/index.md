---
layout: sdk.html
algolia: true
title: mReplace
description:
order: 200
---

# mReplace

Replaces documents in the persistent data storage.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more documents can not be replaced.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {Array} documents
 * @param {object} options
 * @returns {Promise.<Array>}
 */
mReplace (index, collection, documents, options = {})
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `documents` | string | A JSON string containing the documents to update |
| `options` | object | An object containing query options. |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Resolve

Resolves to an array of updated documents.

## Usage

[snippet=m-replace]
