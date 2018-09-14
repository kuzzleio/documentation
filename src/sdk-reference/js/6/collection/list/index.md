---
layout: sdk.html
algolia: true
title: list
description: Returns the collection list of an index
order: 200
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

###### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `from` | int | Start of returned list | `0` |
| `size` | int | Size of returned list | `all` |

## Resolve

Resolve to an object containing the collection list.

## Usage

[snippet=list]
