---
layout: sdk.html.hbs
algolia: true
title: getCurrentUser
description: Returns the profile object for the user linked to the `json web token`
---

# getCurrentUser

Returns informations about the currently logged in user.

## Arguments

```javascript
getCurrentUser (options = null)
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | <pre>object</pre> | Query options

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |

## Resolve

A `User` object representing the current user logged with the SDK.

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `id` | <pre>string</pre> | User ID |
| `content` | User | User content |

The User content has the following properties:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `profileIds` | <pre>array<string></pre> | An array containing the profile ids |
| `_kuzzle_info` | <pre>object</pre> | [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata/) |
| `any` | any | Any other information saved with the user |


## Usage

[snippet=get-current-user]
