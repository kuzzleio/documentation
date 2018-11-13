---
layout: sdk.html.hbs
algolia: true
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the mapping for the given `collection`.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
getMapping(index, collection, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``options`` | Object | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolves

Resolve to an object representing the collection mapping.

## Usage

[snippet=get-mapping]
