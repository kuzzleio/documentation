---
layout: sdk.html.hbs
algolia: true
title: delete
description: Deletes a document from kuzzle
algolia: true
---

# delete

Deletes a document.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (and to no longer be available in search).

## Arguments

```javascript
delete (index, collection, id, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | Optional document ID |
| `options` | <pre>object</pre> | Query options |

### Options

Additional query options

| Options | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolves

Resolves to the id of the deleted document.

## Usage

[snippet=delete]
