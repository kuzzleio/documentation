---
layout: sdk.html
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
deleteMyCredentials (strategy, options = {});
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| `local` | string | Strategy to use    | yes
| `options` | JSON Object | A structure containing query options. | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

A boolean

## Usage

[snippet=delete-my-credentials]
