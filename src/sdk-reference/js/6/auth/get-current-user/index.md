---
layout: sdk.html.hbs
algolia: true
title: getCurrentUser
description:
order: 200
---

# getCurrentUser

Returns the profile object for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```javascript
/**
 * Fetches the current user
 *
 * @param {object} [options]
 * @returns {Promise<User>}
 */
getCurrentUser (options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | object | Query options

### **options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

A User object representing the current user logged with the SDK.

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `id` | string | User ID |
| `content` | User | User content |

The User content contain the following properties:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `profileIds` | array<string> | An array containing the profile ids |
| `_kuzzle_info` | object | [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata/) |
| `any` | any | Any other property savec with the user |


## Usage

[snippet=get-current-user]
