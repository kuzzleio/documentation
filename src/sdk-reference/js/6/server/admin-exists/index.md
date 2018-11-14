---
layout: sdk.html.hbs
algolia: true
title: adminExists
description: Checks that an administrator account exists.
---

# adminExists

{{{since "1.0.0"}}}

Checks that an administrator account exists.

## Arguments

```javascript
/**
* @param {Object} options - {queuable: Boolean(true)}
* @returns {Promise<Object>}
*/
adminExists([options])
```

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | Object | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type   | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | If true, queues the request during downtime, until connected to Kuzzle again | `true`  |

## Resolve

Resolves to a `boolean` set to `true` if an admin exists and `false` if it does not.

## Usage

[snippet=admin-exists]
