---
layout: sdk.html.hbs
algolia: true
title: getMyRights
description: Returns the rights for the user linked to the `JSON Web Token`.
order: 200
---

# getMyRights

Returns the exhaustive list of granted or denied rights for the currently logged in user.

## Signature

```javascript
/**
 * @param {object} [options]
 * @returns {Promise<array<object>>}
 */
getMyRights (options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | object | Query options

### **options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | boolean | Make this request queuable or not | `true`

## Resolve

An `array<object>` containing:

| Property     | Type    | Description                      
| ---------- | ------- | ---------------------------------
| `controller` | string | Controller on wich the rights are applied |
| `action` | string | Action on wich the rights are applied |
| `index` | string | Index on wich the rights are applied |
| `collection` | string | Collection on wich the rights are applied |
| `value` | string | Rights: `allowed` or `restricted` |

## Usage

[snippet=get-my-rights]
