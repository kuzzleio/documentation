---
layout: sdk.html
algolia: true
title: create
description:
order: 200
---

# create

Create a new document in Kuzzle

Throws if a document with the same given id already exists in Kuzzle.

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
create(index, collection, id, body, options)
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | String | Index name | yes |
| `collection` | String | Collection name | yes |
| `id` | String | Optional document id | no |
| `body` | Object | The query to match | yes |
| `options` | Object | An object containing query options. | no |

### Options

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

[snippet=create]
