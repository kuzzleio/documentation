---
layout: sdk.html.hbs
algolia: true
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the mapping for the given `<collection>`.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
getMapping (index, collection, options = null)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``options`` | object | Query options    |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolve to an object representing the collection mapping.

## Usage

[snippet=get-mapping]
