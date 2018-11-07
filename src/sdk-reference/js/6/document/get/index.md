---
layout: sdk.html.hbs
algolia: true
title: get
description:
order: 200
---

# get

Gets a document.

## Arguments

```javascript
get (index, collection, _id, options = {})
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `options` | Object | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolve

## Usage

[snippet=get]
