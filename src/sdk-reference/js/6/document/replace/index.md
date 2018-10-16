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
| `body` | object | The new content of the document to update |
| `options` | object | An object containing query options. |

###### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Resolve

Resolves to an object containing the the document update result.

| Name | Type | Description
| --- | --- | ---
| _id | string | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| result | string | set to `updated` in case of success

## Usage

[snippet=replace]
