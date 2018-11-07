---
layout: sdk.html.hbs
algolia: true
title: create
description:
order: 200
---

# create

Create a new document in Kuzzle

Throws if a document with the same given id already exists in Kuzzle.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```javascript
create(index, collection, id, body, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `id` | String | Optional document id |
| `body` | Object | The query to match |
| `options` | Object | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolve

Resolves to an object containing the document creation result.

| Name | Type | Description
| --- | --- | ---
| _id | String | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| _source | <pre>object</pre> | The created document
| result | <pre>string</pre> | alway

## Usage

[snippet=create]
