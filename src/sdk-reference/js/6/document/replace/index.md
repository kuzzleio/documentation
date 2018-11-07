---
layout: sdk.html
algolia: true
title: replace
description:
order: 200
---

# replace

Replaces the content of an existing document.

## Arguments

```javascript
replace (index, collection, _id, body, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `body` | <pre>object</pre> | The new content of the document to update |
| `options` | <pre>object</pre> | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolve

Resolves to an object containing the the document update result.

| Name | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| result | <pre>string</pre> | set to `updated` in case of success

## Usage

[snippet=replace]
