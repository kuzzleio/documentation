---
layout: sdk.html.hbs
algolia: true
title: adminExists
description: Checks that an administrator account exists.
order: 200
---

# adminExists

{{{since "1.0.0"}}}

Checks that an administrator account exists.

## Signature

```javascript
/**
 * @param {Object} options - {queuable: Boolean(true)}
 * @returns {Promise<Object>}
 */
adminExists([options])
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | Object | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `boolean` containing server information.

## Usage

[snippet=admin-exists]
