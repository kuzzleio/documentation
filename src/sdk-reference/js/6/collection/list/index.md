---
layout: sdk.html.hbs
algolia: true
title: list
description: Returns the collection list of an index
---

# list

Returns the complete list of realtime and stored data collections in requested `<index>` sorted by name in alphanumerical order.

The `from` and `size` arguments in `<options>` allows pagination. They are returned in the response if provided.


## Arguments

```javascript
list (index, options = null)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | <pre>string</pre> | Index name    |
| ``options`` | <pre>object</pre> | Query options    |

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |
| `from` | int | Offset of the first result | `0` |
| `size` | int | Maximum number of returned results | `10` |

## Resolve

Resolve to an object containing the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| ``type`` | <pre>string</pre> | Types of returned collections </br>(`all`, `realtime` or `stored`)   |
| ``collections`` | array&lt;object&gt; | List of collections  |
| `from` | int | Offset of the first result |
| `size` | int | Maximum number of returned results |

Each object in the `collections` array contain the following properties:

| Property   | Type    | Description  |
|--------------|---------|-------------|
| ``name`` | <pre>string</pre> | Collection name |
| ``type`` | <pre>string</pre> | Collection type (`realtime` or `stored`) |

## Usage

[snippet=list]
