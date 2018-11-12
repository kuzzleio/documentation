---
layout: sdk.html.hbs
algolia: true
title: updateSelf
description: Updates the current user object in kuzzle.
---

# updateSelf

Updates the currently logged in user `<content>`.

This route cannot update the list of associated security profiles. To change a user's security profiles, the route [security:updateUser]({{ site_base_path }}api/1/controller-security/update-user) must be used instead.

## Arguments

```javascript
updateSelf (content, options = null)
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | <pre>object</pre> | User custom informations
| `options`  | <pre>object</pre> | Query options


### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |


## Resolve

A User object representing the current user logged with the SDK.

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `id` | <pre>string</pre> | User ID |
| `content` | <pre>object</pre> | User custom informations |

The User content contain the following properties:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `profileIds` | <pre>array<string></pre> | An array containing the profile ids |
| `_kuzzle_info` | <pre>object</pre> | [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata/) |
| `any` | any | Any other information saved with the user |

## Usage

[snippet=update-self]
