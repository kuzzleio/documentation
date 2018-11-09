---
layout: sdk.html.hbs
algolia: true
title: list
description: Returns the collection list of an index
---

# list

Returns the complete list of realtime and stored data collections in requested index sorted by name in alphanumerical order.  
The `from` and `size` arguments allow pagination. They are returned in the response if provided.


## Signature

```javascript
/**
* @param {string} index
* @param {object} [options]
* @returns {Promise.<object>}
 */
list(index, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``options`` | Object | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `from` | int | Offset of the first result | `0` |
| `size` | int | Maximum number of returned results | `10` |

## Resolve

Resolve to an object containing the collection list.

## Usage

[snippet=list]
