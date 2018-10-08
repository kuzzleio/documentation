---
layout: sdk.html.hbs
algolia: true
title: getStrategies
description: Get all authentication strategies registered in Kuzzle.
order: 200
---

# getStrategies

Get all authentication strategies registered in Kuzzle.

## Signature

```javascript
/**
 * Get the list of strategies of the current logged user.
 * 
 * @param options
 * @returns {Promise|*|PromiseLike<T>|Promise<T>}
 */
getStrategies (options = null)
```

## Arguments

| Arguments    | Type    | Description
|--------------|---------|-------------
| `options` | JSON Object | A JSON Object containing the options

### **Options**

Additional query options

| Option     | Type    | Description                       | Default
| ---------- | ------- | --------------------------------- | -------
| `queuable` | bool | Make this request queuable or not | `true`

## Resolve

An array of string.

## Usage

[snippet=get-strategies]
