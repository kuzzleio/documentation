---
layout: sdk.html.hbs
algolia: true
title: list
description: List the indexes
order: 400
---

# list

Get the complete list of data indexes handled by Kuzzle.

## Signature

```javascript
/**
 * @param {object} [options]
 * @returns {Promise<array<string>>}
 */
list (options = null);
```

## Arguments

| Arguments | Type   | Description                         |
| --------- | ------ | ----------------------------------- |
| `options` | object | Query options |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `array<string>` containing the names of Kuzzle's indexes.

## Usage

[snippet=list]
