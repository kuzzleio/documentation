---
layout: sdk.html.hbs
algolia: true
title: updateSelf
description: Updates the current user object in Kuzzle.
order: 200
---

# updateSelf

Updates the current user object in Kuzzle.

## Signature

```javascript
/**
 * @param {object} user
 * @param {object} [options]
 * @returns {Promise<User>}
 */
updateSelf (user, options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | object | the new credentials
| `options`  | object | Query options


### **options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |


## Resolve

A User object representing the current user logged with the SDK.

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `id` | string | The user ID |
| `content` | object | The User content |

The User content contain the following properties:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `profileIds` | array<string> | An array containing the profile ids |
| `_kuzzle_info` | object | [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata/) |
| `any` | any | Any other property savec with the user |

## Usage

[snippet=update-self]
