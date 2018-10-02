---
layout: sdk.html.hbs
algolia: true
title: exists
description: Check if collection exists
order: 200
---

# exists

Check if a collection exists in Kuzzle.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} [options]
 * @returns {Promise.<boolean>}
 */
exists(index, collection, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``options`` | Object | An object containing query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolve to a boolean indicating if the collection exists

## Usage

[snippet=exists]
