---
layout: sdk.html.hbs
algolia: true
title: getMyRights
description: Returns the rights for the user linked to the `json web token`.
---

# getMyRights

Returns the exhaustive list of granted or denied rights for the currently logged in user.

## Arguments

```javascript
getMyRights (options = null)
```

<br/>

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | <pre>object</pre> | Query options

### **options**

Additional query options

| Property     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`

## Resolve

An `array<object>` containing:

| Property     | Type    | Description
| ---------- | ------- | ---------------------------------
| `controller` | <pre>string</pre> | Controller on wich the rights are applied |
| `action` | <pre>string</pre> | Action on wich the rights are applied |
| `index` | <pre>string</pre> | Index on wich the rights are applied |
| `collection` | <pre>string</pre> | Collection on wich the rights are applied |
| `value` | <pre>string</pre> | Rights: `allowed` or `restricted` |

## Usage

[snippet=get-my-rights]
