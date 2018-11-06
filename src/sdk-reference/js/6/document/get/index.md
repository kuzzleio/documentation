---
layout: sdk.html.hbs
algolia: true
title: get
description:
order: 200
---

# get

Given a document id, retrieves the corresponding document from the database.

Only documents in the persistent data storage layer can be retrieved.

## Arguments

```javascript
get (index, collection, _id, options = {})
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `options` | Object | An object containing query options. |

###### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |

## Resolve

## Usage

[snippet=get]
