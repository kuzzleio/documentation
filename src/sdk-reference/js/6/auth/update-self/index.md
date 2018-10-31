---
layout: sdk.html.hbs
algolia: true
title: updateSelf
description: Updates the current user object in Kuzzle.
order: 200
---

# updateSelf

Updates the currently logged in user `<content>`.

This route cannot update the list of associated security profiles. To change a user's security profiles, the route [security:updateUser]({{ site_base_path }}api/1/controller-security/update-user) must be used instead.

## Signature

```javascript
/**
 * @param {object} user
 * @param {object} [options]
 * @returns {Promise<User>}
 */
updateSelf (content, options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | object | User custom informations
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
| `id` | string | User ID |
| `content` | object | User custom informations |

The User content contain the following properties:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `profileIds` | array<string> | An array containing the profile ids |
| `_kuzzle_info` | object | [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata/) |
| `any` | any | Any other information saved with the user |

## Usage

[snippet=update-self]
