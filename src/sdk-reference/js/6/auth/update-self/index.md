---
layout: sdk.html
algolia: true
title: updateSelf
description: Updates the current user object in Kuzzle.
order: 200
---

# updateSelf

Updates the current user object in Kuzzle.

## Signature

```javascript
/**
 * @param {object} body - a plain javascript object representing the user's modification
 * @param {object} [options] - (optional) arguments
 * @returns {Kuzzle} this object
 */
updateSelf (body, options = {})
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `content` | JSON Object | the new credentials
| `options`  | JSON Object | A JSON Object containing the optionsoptions


###### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |


## Resolve

A User object.

## Usage

[snippet=update-self]
