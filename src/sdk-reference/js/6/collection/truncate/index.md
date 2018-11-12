---
layout: sdk.html.hbs
algolia: true
title: truncate
description: Remove all documents from collection
---

# truncate

Remove all documents from a `<collection>` while keeping the associated mapping.

It is faster than deleting all documents from a collection.

## Arguments

```javascript
truncate (index, collection, options = null)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | <pre>string</pre> | Index name    |
| ``collection`` | <pre>string</pre> | Collection name    |
| ``options`` | <pre>object</pre> | Query options    |

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |

## Resolve

Resolve if the collection is successfully truncated.

## Usage

[snippet=truncate]
