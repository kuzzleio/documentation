---
layout: sdk.html.hbs
algolia: true
title: deleteMyCredentials
description:
order: 200
---

# deleteMyCredentials

Deletes credentials for a specific `<strategy>` associated to the current user.

If the credentials that generated the current JWT are removed, the user will remain logged in until they log out or their session expire. After that, they will no longer be able to log in with the deleted credentials.

## Signature

```javascript
/**
 * @param {string} strategy
 * @param {object} [options]
 * @returns {Promise<object>}
 */
deleteMyCredentials (strategy, options = null);
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `strategy` | string | Strategy to use
| `options` | object | Query options

### **options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

A boolean indicating if the credentials are being deleted.

## Usage

[snippet=delete-my-credentials]
