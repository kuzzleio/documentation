---
layout: sdk.html.hbs
algolia: true
title: updateSelf
description: Updates the current user object in Kuzzle.
---

# updateSelf

Updates the current user object in Kuzzle.

## Signature

```javascript
/**
 * Update the current user in Kuzzle.
 * 
 * @param {object} body - a plain javascript object representing the user's modification
 * @param {object} [options] - (optional) arguments
 * @returns {Kuzzle} this object
 */
updateSelf (body, options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | JSON Object | the new credentials
| `options`  | JSON Object | A JSON Object containing the options


### **Options**

Additional query options

| Property     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |


## Resolve

A User object.

## Usage

[snippet=update-self]
