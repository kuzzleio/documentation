---
layout: sdk.html.hbs
algolia: true
title: exists
description: Test if an index exists
order: 300
---

# exists

Checks if the given `<index>` exists in Kuzzle.

## Signature

```javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise<boolean>}
 */
exists (index, options = null);
```

## Arguments

| Arguments | Type   | Description    |
| --------- | ------ | ---------------|
| `index`   | string | Index name     |
| `options` | object | Query options  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to a `boolean` that indicate whether the index exists or not.

## Usage

[snippet=exists]
