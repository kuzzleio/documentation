---
layout: sdk.html.hbs
algolia: true
title: createOrReplace
description:
order: 200
---

# createOrReplace

Creates a new document in the persistent data storage, or replace it if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {string|null} id
 * @param {object} body
 * @param {object} options
 * @returns {Promise.<object>}
 */
createOrReplace(index, collection, id, body, options)
```
## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `id` | String | Optional document id |
| `body` | Object | The query to match |
| `options` | Object | An object containing query options. |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Resolve

Resolves to an object containing the document creation result.

| Name | Type | Description
| --- | --- | ---
| _id | String | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| _source | object | The created document
| result | string | alway

## Usage

[snippet=create-or-replace]
