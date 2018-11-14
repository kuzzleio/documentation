---
layout: sdk.html.hbs
algolia: true
title: getConfig
description: Returns the current Kuzzle configuration.
order: 200
---

# getConfig

{{{since "1.0.0"}}}

Returns the current Kuzzle configuration.

<div class="alert alert-warning">
  This route should only be accessible to administrators, as it might return sensitive information about the backend.
</div>

## Signature

```javascript
/**
 * @param {Object} options - {queuable: Boolean(true)}
 * @returns {Promise<Object>}
 */
getConfig([options])
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

Resolves to an `Object` containing server configuration.

## Usage

[snippet=get-config]
