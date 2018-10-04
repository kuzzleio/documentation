---
layout: sdk.html
algolia: true
title: replace
description:
order: 200
---

# replace

Replaces an existing document in the persistent data storage.
Only documents in the persistent data storage layer can be replaced.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {string} _id
 * @param {object} body
 * @param {object} options
 * @returns {Promise.<object>}
 */
replace (index, collection, _id, body, options = {})
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `id` | string | The document id |
| `body` | string | A JSON string containing the body of the document |
| `options` | object | An object containing query options. |

###### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Resolve

## Usage

[snippet=replace]
