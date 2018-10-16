---
layout: sdk.html.hbs
algolia: true
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
order: 200
---

# getMyRights

Returns the rights for the user linked to the `JSON Web Token`, provided in the query or the `Authorization` header.

## Signature

```javascript
/**
 * Gets the rights array of the currently logged user.
 * 
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
getMyRights (options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | JSON Object | A JSON Object containing the options

### **Options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | bool | Make this request queuable or not | `true`

## Resolve

An array of object containing:

| Property     | Type    | Description                      
| ---------- | ------- | ---------------------------------
| `controller` | string | The controller on wich the rights are applied |
| `action` | string | The action on wich the rights are applied |
| `index` | string | The index on wich the rights are applied |
| `collection` | string | The collection on wich the rights are applied |
| `value` | string | The rights |

## Usage

[snippet=get-my-rights]
