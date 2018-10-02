---
layout: sdk.html
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
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
getCurrentUser (options = {})
```

## Resolve

The profile object for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

| Option     | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `id` | string | The user ID |
| `content` | JSON Object | The user content |
| `profileIds` | JSON Array | An array containing the profile ids |


## Usage

[snippet=get-current-user]
