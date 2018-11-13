---
layout: sdk.html.hbs
algolia: true
title: getCurrentUser
description: Returns the profile object for the user linked to the json web token
---

# getCurrentUser

Returns information about the currently logged in user.

## Arguments

```javascript
getCurrentUser ([options])
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | <pre>object</pre> | Query options

### options

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolves

A `User` object representing the current user logged with the SDK.

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `id` | <pre>string</pre> | User ID |
| `content` | <pre>User</pre> | User content |

The User content has the following properties:

| Property     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `profileIds` | <pre>string[]</pre> | An array containing the profile ids |
| `_kuzzle_info` | <pre>object</pre> | [Kuzzle metadata]({{ site_base_path }}guide/1/essentials/document-metadata/) |
| `any` | <pre>any</pre> | Any other information saved with the user |


## Usage

[snippet=get-current-user]
