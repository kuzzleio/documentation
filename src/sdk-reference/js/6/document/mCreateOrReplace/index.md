---
layout: sdk.html.hbs
algolia: true
title: mCreateOrReplace
description:
order: 200
---

# mCreateOrReplace

Creates or replaces new documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents fail to create.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} body
 * @param {object} options
 * @returns {Promise.<object>}
 */
mCreateOrReplace (index, collection, documents, options = {})
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `body` | string | A JSON string containing the documents to create |
| `options` | Object | An object containing query options. |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Resolve

Resolves to an object containing the created documents.

## Usage

[snippet=m-create-or-replace]
