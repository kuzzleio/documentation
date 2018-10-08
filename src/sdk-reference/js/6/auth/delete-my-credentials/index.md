---
layout: sdk.html.hbs
algolia: true
title: deleteMyCredentials
description:
order: 200
---

# deleteMyCredentials

## Signature

```javascript
/**
 * Delete credentials of the specified <strategy> for the current user.
 *
 * @param strategy
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
deleteMyCredentials (strategy, options = null);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | Strategy to use
| `options` | JSON Object | A JSON Object containing the options

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

A boolean

## Usage

[snippet=delete-my-credentials]
